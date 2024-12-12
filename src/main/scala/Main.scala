package Main

import java.io.*
import Converter.{GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
import Filter.{FlipImageFilter, InversionFilter, RotationFilter}
import Loader.MyFileImageLoader
import Models.conversionTable.PaulBorkeTable

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

@main def main (args: String*): Unit = {
  println("Hello there")
  val file = args(0)

  val image: BufferedImage = ImageIO.read(new File(file))
  if (image == null) {
    println("Couldn't load")
    return
  }

  // load image
  val loader = MyFileImageLoader(file)
  val rgbImage = loader.load()
  //rgbImage.print()

  // convert to grayscale
  val greyConverter = RGBtoGreyScaleConverter()
  val greyImage = greyConverter.convert(rgbImage)
  //greyImage.print()

  // invert
  //val invertFilter = InversionFilter()
  //val invertedImage = invertFilter.applyFilter(greyImage)

  // flip
  //val flipFilter = FlipImageFilter('x')
  //val flippedImage = flipFilter.applyFilter(invertedImage)

  // rotate
  val rotateFilter = RotationFilter(180)
  val rotatedImage = rotateFilter.applyFilter(greyImage)

  // generate ASCII art
  val asciiConverter = GreyScaleToASCIIConverter(new PaulBorkeTable)
  //val asciiImage = asciiConverter.convert(flippedImage)
  val asciiImage = asciiConverter.convert(rotatedImage)

  // to string
  val result = asciiImage.toASCIIString

  // to file
  val filePath = "src/main/scala/Resources/result.txt"

  val fileWriter = new FileWriter(filePath)
  try {
    fileWriter.write(result)
  } finally {
    fileWriter.close() // Ensure the writer is closed after writing
  }

  // resize image???
  // apply filters
  // export
  // UI
  // refactor for error handling
  // tests
  // we did it lets go
}
