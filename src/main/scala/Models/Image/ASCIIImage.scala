package Models.Image

import Models.Image.visitor.ImageVisitor
import Models.Pixel.ASCIIPixel
import Models.Image.Image

case class ASCIIImage (pixels: Vector[Vector[ASCIIPixel]]) extends Image {
  override def getPixel(x: Int, y: Int): ASCIIPixel = {
    pixels(x)(y)
  }

  override def getPixels: Vector[Vector[ASCIIPixel]] = pixels

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitASCIIImage(this)
  }

}
