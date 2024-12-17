package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.{GreyScalePixel, Pixel}

case class GreyScaleImage (pixels: Vector[Vector[GreyScalePixel]]) extends Image {
  override def getPixel(x: Int, y: Int): GreyScalePixel = {
    pixels(x)(y)
  }

  override def getPixels: Vector[Vector[GreyScalePixel]] = pixels

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitGreyScaleImage(this)
  }
}
