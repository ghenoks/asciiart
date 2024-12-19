package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.RGBPixel
import models.PixelArray

case class RGBImage (pixelArray: PixelArray[RGBPixel]) extends Image {
  override def getPixel(x: Int, y: Int): RGBPixel = {
    pixelArray.getPixel(x, y).getOrElse(
      throw new IllegalArgumentException(s"Pixel at ($x, $y) is out of bounds")
    )
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitRGBImage(this)
  }
}
