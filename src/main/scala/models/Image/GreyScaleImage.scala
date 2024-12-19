package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.GreyScalePixel
import models.PixelArray

case class GreyScaleImage (pixelArray: PixelArray[GreyScalePixel]) extends Image {
  override def getPixel(x: Int, y: Int): GreyScalePixel = {
    pixelArray.getPixel(x, y).getOrElse(
      throw new IllegalArgumentException(s"Pixel at ($x, $y) is out of bounds")
    )
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitGreyScaleImage(this)
  }
}
