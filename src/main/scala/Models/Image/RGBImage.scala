package Models.Image

import Models.Image.visitor.ImageVisitor
import Models.Pixel.{Pixel, RGBPixel}

case class RGBImage (pixels: Vector[Vector[RGBPixel]]) extends Image {
  override def getPixel(x: Int, y: Int): RGBPixel = {
    pixels (x)(y)
  }

  override def getPixels: Vector[Vector[RGBPixel]] = pixels

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitRGBImage(this)
  }
}
