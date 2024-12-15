package Main

import java.io.*
import Converter.{GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import Exporter.{ASCIIImageExporter, FileOutputExporter, StdOutputExporter}
import Filter.{BrightnessFilter, FlipImageFilter, InversionFilter, RotationFilter, ScaleFilter}
import Loader.MyFileImageLoader
import Models.conversionTable.PaulBorkeTable
import UI.ImageToStringVisitor

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

@main def main (args: String*): Unit = {
  println("Hello there")
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
  val scaleFilter = ScaleFilter(0.25)
  val scaledImage = scaleFilter.applyFilter(greyImage)

  // generate ASCII art
  val asciiConverter = GreyScaleToASCIIConverter(new PaulBorkeTable)
  //val asciiImage = asciiConverter.convert(flippedImage)
  //val asciiImage = asciiConverter.convert(brigthImage)
  //val asciiImage = asciiConverter.convert(invertedImage)
  val asciiImage = asciiConverter.convert(scaledImage)
  //val asciiImage = asciiConverter.convert(greyImage)

  // to file
  val filePath = "src/main/scala/Resources/result.txt"

  // export
  val fileExport = new File(filePath)
  val fileExporter = FileOutputExporter(fileExport)
  val consoleVisitor = new ImageToStringVisitor()
  //val stdExporter = StdOutputExporter()

  val imageExporter = ASCIIImageExporter(fileExporter, consoleVisitor)
  //val imageExporter = ASCIIImageExporter(stdExporter, consoleVisitor)
  imageExporter.output(asciiImage)

  // UI
  // refactor for error handling
  // tests
  // we did it lets go

  // create controller
  // create view
  // controller.run()

  // argument parser vrati --command + doplnek
  // module getter vrati moduleHolder (nova trida)
}
