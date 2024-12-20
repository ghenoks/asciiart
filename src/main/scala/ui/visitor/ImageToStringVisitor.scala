package ui.visitor

import models.Image.visitor.ImageVisitor
import models.Image.{ASCIIImage, GreyScaleImage, RGBImage}

import scala.collection.mutable.ArrayBuffer

class ImageToStringVisitor extends ImageVisitor[String] {

  override def visitASCIIImage(image: ASCIIImage): String = {
    val height = image.getHeight
    val width = image.getWidth

    val arr: ArrayBuffer[String] = ArrayBuffer[String]()

    for (x <- 0 until height) {
      val rowArr: ArrayBuffer[Char] = ArrayBuffer[Char]()
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) => rowArr += pixel.getValue
          case Left(error) => throw IllegalArgumentException(error.message)
        }
      }
      arr += rowArr.mkString
    }
    arr.mkString("\n")
  }

  override def visitRGBImage(image: RGBImage): String = {
    "Rendering to string not supported for RGBImage."
  }

  override def visitGreyScaleImage(image: GreyScaleImage): String = {
    "Rendering to string not supported for GreyScaleImage."
  }
}
