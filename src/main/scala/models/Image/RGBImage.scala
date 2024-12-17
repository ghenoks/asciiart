package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.{Pixel, RGBPixel}

case class RGBImage (pixels: Vector[Vector[RGBPixel]]) extends Image {
  override def getPixel(x: Int, y: Int): RGBPixel = {
    pixels (x)(y)
  }

  override def getPixels: Vector[Vector[RGBPixel]] = pixels

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitRGBImage(this)
  }
}
