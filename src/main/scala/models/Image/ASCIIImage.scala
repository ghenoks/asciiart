package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.ASCIIPixel
import models.Image.Image

case class ASCIIImage (pixels: Vector[Vector[ASCIIPixel]]) extends Image {
  override def getPixel(x: Int, y: Int): ASCIIPixel = {
    pixels(x)(y)
  }

  override def getPixels: Vector[Vector[ASCIIPixel]] = pixels

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitASCIIImage(this)
  }

}
