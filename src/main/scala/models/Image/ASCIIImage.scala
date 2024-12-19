package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.ASCIIPixel
import models.PixelArray

case class ASCIIImage (pixelArray: PixelArray[ASCIIPixel]) extends Image {
  override def getPixel(x: Int, y: Int): ASCIIPixel = {
    pixelArray.getPixel(x, y).getOrElse(
      throw new IllegalArgumentException(s"Pixel at ($x, $y) is out of bounds")
    )
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitASCIIImage(this)
  }
}
