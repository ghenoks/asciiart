package ui.moduleGetter

import converter.{ASCIIConverter, GreyScaleConverter, GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import exporter.*
import filter.*
import loader.{ImageLoader, RandomImageLoader, StdFileImageLoader}
import models.Image.{GreyScaleImage, Image, RGBImage}
import models.Pixel.GreyScalePixel
import models.conversionTable.{LinearConversionTable, NonLinearPaulBourkeTable, PaulBourkeTable}
import models.{Argument, Axis, ModuleHolder}

import java.io.File
import scala.collection.mutable.ListBuffer
import scala.util.Try

class MyModuleGetter(moduleList: List[Argument]) extends ModuleGetter[ModuleHolder] {

  override def getModules: Either[String, ModuleHolder] = {
    for {
      loader <- getLoader.left.map(error => s"Loader error: $error")
      greyScaleConverter <- getGreyScaleConverter.left.map(error => s"GreyScaleConverter error: $error")
      filter <- getFilters.left.map(error => s"Filter error: $error")
      asciiConverter <- getASCIIConverter.left.map(error => s"ASCIIConverter error: $error")
      exporter <- getExporter.left.map(error => s"Exporter error: $error")
    } yield new ModuleHolder(loader, greyScaleConverter, filter, asciiConverter, exporter)
  }

  private def getLoader: Either[String, ImageLoader] = {
    val imageCommands: Seq[Argument] = moduleList.filter(arg => arg.name == "image" || arg.name == "image-random")

    if (imageCommands.isEmpty) Left("No image command specified")
    else if (imageCommands.length > 1) Left("Multiple image commands specified. Please specify only one image command.")
    else {
      val imageCommand = imageCommands.head
      imageCommand.name match {
        case "image" =>
          imageCommand.value match {
            case Some(imagePath) =>
              if (imagePath.endsWith(".jpg") || imagePath.endsWith(".png")) {
                Right(StdFileImageLoader(imagePath))
              } else {
                Left("Unsupported image format. Please use JPG or PNG file.")
              }
            case None =>
              Left("No image path provided for 'image' command.")
          }
        case "image-random" =>
          Right(RandomImageLoader())
        case _ =>
          Left("Unknown image command.")
      }
    }
  }

  private def getGreyScaleConverter: Either[String, GreyScaleConverter[RGBImage]] = {
    Right(RGBtoGreyScaleConverter())
  }

  private def getFilters: Either[String, ImageFilter[GreyScaleImage]] = {
    val imageFilters = ListBuffer[ImageFilter[GreyScaleImage]]()
    val filterCommands: Seq[Argument] = moduleList.filter(arg => arg.name == "scale" || arg.name == "invert" || arg.name == "rotate" || arg.name == "brightness" || arg.name == "flip")

    if (filterCommands.isEmpty) {
      return Right(GSImageIdentityFilter())
    }

    var error: Option[String] = None

    for (command <- filterCommands if error.isEmpty) {
      command match {
        case Argument("invert", None) => imageFilters += InversionFilter()
        case Argument("invert", Some(value)) =>
          error = Some("Argument specified after inversion filter")

        case Argument("rotate", None) =>
          error = Some("Rotation filter without argument")
        case Argument("rotate", Some(value)) =>
          value match {
            case "90" | "+90" | "-270" => imageFilters += RotationFilter(90)
            case "180" | "+180" | "-180" => imageFilters += RotationFilter(180)
            case "270" | "+270" | "-90" => imageFilters += RotationFilter(270)
            case "0" | "+360" | "-360" => imageFilters += RotationFilter(0)
            case _ => error = Some("Invalid rotate argument")
          }

        case Argument("brightness", None) =>
          error = Some("Brightness filter without argument")
        case Argument("brightness", Some(value)) =>
          if (value.matches("[+-]?\\d+")) {
            imageFilters += BrightnessFilter(value.toInt)
          } else {
            error = Some(s"Invalid brightness value: $value. Expected an integer.")
          }

        case Argument("flip", None) =>
          error = Some("Flip filter without argument")
        case Argument("flip", Some(value)) =>
          value match {
            case "x" => imageFilters += FlipImageFilter(Axis.X)
            case "y" => imageFilters += FlipImageFilter(Axis.Y)
            case _ => error = Some("Flip filter invalid argument")
          }

        case Argument("scale", None) =>
          error = Some("Scale filter without argument")
        case Argument("scale", Some(value)) =>
          if (Try(value.toFloat).isSuccess) imageFilters += ScaleFilter(value.toFloat)
          else error = Some(s"Invalid scale value: $value. Expected a float.")

        case _ =>
      }
    }

    error match {
      case Some(errMsg) => Left(errMsg)
      case None =>
        if (imageFilters.isEmpty) Right(GSImageIdentityFilter())
        else if (imageFilters.length == 1) Right(imageFilters.head)
        else Right(MixedFilter(imageFilters.toList))
    }
  }

  private def getASCIIConverter: Either[String, ASCIIConverter[GreyScaleImage, GreyScalePixel]] = {
    val tableCommands: Seq[Argument] = moduleList.filter(arg => arg.name == "table" || arg.name == "custom-table")

    if (tableCommands.length > 1) Left("Multiple table commands specified. Please specify only one table command.")
    else if (tableCommands.isEmpty) Right(GreyScaleToASCIIConverter(PaulBourkeTable()))
    else {
      val tableCommand = tableCommands.head
      tableCommand.name match {
        case "table" =>
          tableCommand.value match {
            case Some("paulbourke") => Right(GreyScaleToASCIIConverter(PaulBourkeTable()))
            case Some("nonlinear-paulbourke") => Right(GreyScaleToASCIIConverter(NonLinearPaulBourkeTable()))
            case _ => Left("Missing table info")
          }
        case "custom-table" =>
          tableCommand.value match {
            case Some(value) => Right(GreyScaleToASCIIConverter(LinearConversionTable(value)))
            case _ => Left("Missing table info")
          }
      }
    }
  }

  private def getExporter: Either[String, Exporter[Image]] = {
    val imageExporters = ListBuffer[Exporter[Image]]()
    val exportCommands: Seq[Argument] = moduleList.filter(arg => arg.name == "output-console" || arg.name == "output-file")

    if (exportCommands.isEmpty) {
      return Left("No output command specified")
    }

    var error: Option[String] = None

    for (command <- exportCommands if error.isEmpty) {
      command match {
        case Argument("output-console", _) =>
          imageExporters += ImageExporter(StdOutputExporter())
        case Argument("output-file", Some(filePath)) =>
          imageExporters += ImageExporter(FileOutputExporter(new File(filePath)))
        case Argument("output-file", None) =>
          error = Some("Output file command specified without a file path")
        case _ =>
          error = Some("Missing output command")
      }
    }

    error match {
      case Some(errMsg) => Left(errMsg)
      case None =>
        if (imageExporters.length == 1) Right(imageExporters.head)
        else Right(MixedImageExporter(imageExporters.toList))
    }
  }
}
