package ui

import models.Image.{ASCIIImage, GreyScaleImage, RGBImage}
import models.Image.visitor.ImageVisitor

class ImageToStringVisitor extends ImageVisitor[String] {

  override def visitASCIIImage(image: ASCIIImage): String = {
    val pixels = image.getPixels
    pixels.map(row => row.map(_.getValue).mkString).mkString("\n")
  }

  override def visitRGBImage(image: RGBImage): String = {
    "Rendering to string not supported for RGBImage."
  }

  override def visitGreyScaleImage(image: GreyScaleImage): String = {
    "Rendering to string not supported for RGBImage."
  }
}
