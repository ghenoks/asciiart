package ui

import models.Image.{ASCIIImage, GreyScaleImage, RGBImage}
import models.Image.visitor.ImageVisitor

import scala.collection.mutable.ArrayBuffer

class ImageToStringVisitor extends ImageVisitor[String] {

  override def visitASCIIImage(image: ASCIIImage): String = {
    val height = image.getHeight
    val width = image.getWidth
    println(height)
    println(width)
    val arr: ArrayBuffer[String] = ArrayBuffer[String]()

    for (x <- 0 until height) {
      val rowArr: ArrayBuffer[Char] = ArrayBuffer[Char]()
      for (y <- 0 until width) {
        rowArr += image.getPixel(x, y).getValue
      }
      arr += rowArr.mkString
    }
    val result = arr.mkString("\n")
    println(result.length)
    result
  }

  override def visitRGBImage(image: RGBImage): String = {
    "Rendering to string not supported for RGBImage."
  }

  override def visitGreyScaleImage(image: GreyScaleImage): String = {
    "Rendering to string not supported for RGBImage."
  }
}
