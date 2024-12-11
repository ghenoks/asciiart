package Main

import java.io._

import Converter.{GreyScaleToASCIIConverter, RGBtoGreyScaleConverter}
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
    println("Couldnt load")
    return
  }
  //println(s"Width: ${image.getWidth}")
  //println(s"Height: ${image.getHeight}")
  //println(s"Image Type: ${image.getType}")

  // load image
  val loader = MyFileImageLoader(file)
  val rgbImage = loader.load()
  //rgbImage.print()
  
  // convert to grayscale
  val greyConverter = RGBtoGreyScaleConverter()
  val greyImage = greyConverter.convert(rgbImage)
  //greyImage.print()

  // map grayscale values to ASCII characters
  // generate ASCII art

  val asciiConverter = GreyScaleToASCIIConverter(new PaulBorkeTable)
  val asciiImage = asciiConverter.convert(greyImage)
  val result = asciiImage.toASCIIString

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
