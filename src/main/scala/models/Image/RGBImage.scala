package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.RGBPixel
import models.PixelArray

case class RGBImage (pixelArray: PixelArray[RGBPixel]) extends Image {
  override def getPixel(x: Int, y: Int): RGBPixel = {
    pixelArray.getPixel(x, y)
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitRGBImage(this)
  }
}
