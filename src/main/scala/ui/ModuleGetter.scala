package ui

import converter.{ASCIIConverter, GreyScaleConverter, GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import exporter.{ASCIIImageExporter, Exporter, FileOutputExporter, StdOutputExporter}
import filter.{BrightnessFilter, FlipImageFilter, ImageFilter, ImageIdentityFilter, InversionFilter, MixedFilter, RotationFilter, ScaleFilter}
import loader.{ImageLoader, MyFileImageLoader, MyRandomImageLoader}
import models.Image.{GreyScaleImage, Image, RGBImage}
import models.ModuleHolder
import models.Pixel.GreyScalePixel
import models.conversionTable.{LinearConversionTable, PaulBorkeTable}

import java.io.File
import scala.collection.mutable.ListBuffer
import scala.util.Try

class ModuleGetter(moduleList: List[(String, Option[String])]) {

  def getModules: Either[String, ModuleHolder] = {
    for {
      loader <- getLoader.left.map(error => s"Loader error: $error")
      greyScaleConverter <- getGreyScaleConverter.left.map(error => s"GreyScaleConverter error: $error")
      filter <- getFilters.left.map(error => s"Filter error: $error")
      asciiConverter <- getASCIIConverter.left.map(error => s"ASCIIConverter error: $error")
      exporters <- getExporter.left.map(error => s"Exporter error: $error")
    } yield new ModuleHolder(loader, greyScaleConverter, filter, asciiConverter, exporters)
  }

  private def getLoader: Either[String, ImageLoader] = {
    val imageCommands: Seq[(String, Option[String])] = moduleList.filter(arg => arg._1 == "image" || arg._1 == "image-random")

    if (imageCommands.isEmpty) Left("No image command specified")
    else if (imageCommands.length > 1) Left("Multiple image commands specified. Please specify only one image command.")
    else {
      val imageCommand = imageCommands.head
      imageCommand._1 match {
        case "image" =>
          imageCommand._2 match {
            case Some(imagePath) =>
              if (imagePath.endsWith(".jpg") || imagePath.endsWith(".png")) {
                Right(MyFileImageLoader(imagePath))
              } else {
                Left("Unsupported image format. Please use JPG or PNG file.")
              }
            case None =>
              Left("No image path provided for 'image' command.")
          }
        case "image-random" =>
          Right(MyRandomImageLoader())
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
    val filterCommands: Seq[(String, Option[String])] = moduleList.filter(arg => arg._1 == "scale" || arg._1 == "invert" || arg._1 == "rotate" || arg._1 == "brightness" || arg._1 == "flip")

    if (filterCommands.isEmpty) {
      return Right(ImageIdentityFilter())
    }

    var error: Option[String] = None

    for (command <- filterCommands if error.isEmpty) {
      command match {
        case ("invert", None) => imageFilters += InversionFilter()
        case ("invert", Some(value)) =>
          error = Some("Argument specified after inversion filter")

        case ("rotate", None) =>
          error = Some("Rotation filter without argument")
        case ("rotate", Some(value)) =>
          value match {
            case "90" | "+90" | "-270" => imageFilters += RotationFilter(90)
            case "180" | "+180" | "-180" => imageFilters += RotationFilter(180)
            case "270" | "+270" | "-90" => imageFilters += RotationFilter(270)
            case "0" | "+360" | "-360" => imageFilters += RotationFilter(0)
            case _ => error = Some("Invalid rotate argument")
          }

        case ("brightness", None) =>
          error = Some("Brightness filter without argument")
        case ("brightness", Some(value)) =>
          if (value.matches("[+-]?\\d+")) {
            imageFilters += BrightnessFilter(value.toInt)
          } else {
            error = Some(s"Invalid brightness value: $value. Expected an integer.")
          }

        case ("flip", None) =>
          error = Some("Flip filter without argument")
        case ("flip", Some(value)) =>
          value match {
            case "x" => imageFilters += FlipImageFilter('x')
            case "y" => imageFilters += FlipImageFilter('y')
            case _ => error = Some("Flip filter invalid argument")
          }

        case ("scale", None) =>
          error = Some("Scale filter without argument")
        case ("scale", Some(value)) =>
          if (Try(value.toFloat).isSuccess) imageFilters += ScaleFilter(value.toFloat)
          else error = Some(s"Invalid scale value: $value. Expected a float.")

        case _ =>
      }
    }

    error match {
      case Some(errMsg) => Left(errMsg)
      case None =>
        if (imageFilters.isEmpty) Right(ImageIdentityFilter())
        else if (imageFilters.length == 1) Right(imageFilters.head)
        else Right(MixedFilter(imageFilters.toList))
    }
  }

  private def getASCIIConverter: Either[String, ASCIIConverter[GreyScaleImage, GreyScalePixel]] = {
    val tableCommands: Seq[(String, Option[String])] = moduleList.filter(arg => arg._1 == "table" || arg._1 == "custom-table")

    if (tableCommands.length > 1) Left("Multiple table commands specified. Please specify only one table command.")
    else if (tableCommands.isEmpty) Right(GreyScaleToASCIIConverter(PaulBorkeTable()))
    else {
      val tableCommand = tableCommands.head
      tableCommand._1 match {
        case "table" =>
          tableCommand._2 match {
            case Some("paulbourke") => Right(GreyScaleToASCIIConverter(PaulBorkeTable()))
            case _ => Left("Missing table info")
          }
        case "custom-table" =>
          tableCommand._2 match {
            case Some(value) => Right(GreyScaleToASCIIConverter(LinearConversionTable(value)))
            case _ => Left("Missing table info")
          }
      }
    }
  }

  private def getExporter: Either[String, List[Exporter[Image]]] = {
    val imageExporters = ListBuffer[Exporter[Image]]()
    val exportCommands: Seq[(String, Option[String])] = moduleList.filter(arg => arg._1 == "output-console" || arg._1 == "output-file")

    if (exportCommands.isEmpty) {
      return Left("No output command specified")
    }

    var error: Option[String] = None

    for (command <- exportCommands if error.isEmpty) {
      command match {
        case ("output-console", _) =>
          imageExporters += ASCIIImageExporter(StdOutputExporter())
        case ("output-file", Some(filePath)) =>
          imageExporters += ASCIIImageExporter(FileOutputExporter(new File(filePath)))
        case ("output-file", None) =>
          error = Some("Output file command specified without a file path")
        case _ =>
          error = Some("Missing output command")
      }
    }

    error match {
      case Some(errMsg) => Left(errMsg)
      case None => Right(imageExporters.toList)
    }
  }
}
