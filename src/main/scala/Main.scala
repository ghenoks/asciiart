package Main

import converter.{GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import exporter.{ASCIIImageExporter, FileOutputExporter, StdOutputExporter}
import filter.{InversionFilter, ScaleFilter}
import loader.MyFileImageLoader
import models.conversionTable.PaulBorkeTable
import ui.controller.ConsoleController

import java.io.File

@main def main (args: String*): Unit = {

  /*println("Hello there")
  val file = args(0)

  // load image
  val loader = MyFileImageLoader(file)
  val rgbImage = loader.load()
  //rgbImage.print()

  // convert to grayscale
  val greyConverter = RGBtoGreyScaleConverter()
  val greyImage = greyConverter.convert(rgbImage)
  //greyImage.print()

  // apply filters
  // invert
  val invertFilter = InversionFilter()
  val invertedImage = invertFilter.applyFilter(greyImage)

  // flip
  //val flipFilter = FlipImageFilter('x')
  //val flippedImage = flipFilter.applyFilter(invertedImage)

  // rotate
  //val rotateFilter = RotationFilter(270)
  //val rotatedImage = rotateFilter.applyFilter(greyImage)

  // brightness
  //val brightFilter = BrightnessFilter(100)
  //val brigthImage = brightFilter.applyFilter(invertedImage)

  // scale
  //val scaleFilter = ScaleFilter(0.25)
  //val scaledImage = scaleFilter.applyFilter(greyImage)

  // generate ASCII art
  val asciiConverter = GreyScaleToASCIIConverter(new PaulBorkeTable)
  //val asciiImage = asciiConverter.convert(flippedImage)
  //val asciiImage = asciiConverter.convert(brigthImage)
  //val asciiImage = asciiConverter.convert(invertedImage)
  val asciiImage = asciiConverter.convert(greyImage)
  //val asciiImage = asciiConverter.convert(greyImage)

  // to file
  val filePath = "src/main/scala/resources/result.txt"

  // export
  val fileExport = new File(filePath)
  val fileExporter = FileOutputExporter(fileExport)
  //val stdExporter = StdOutputExporter()

  val imageExporter = ASCIIImageExporter(fileExporter)
  //val imageExporter = ASCIIImageExporter(stdExporter)
  imageExporter.output(asciiImage)*/

  val controller = ConsoleController(args.toList)
  controller.run()
}
