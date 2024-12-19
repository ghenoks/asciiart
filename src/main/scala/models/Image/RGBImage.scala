package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.RGBPixel
import models.{BusinessError, PixelArray}

case class RGBImage (pixelArray: PixelArray[RGBPixel]) extends Image {
  override def getPixel(x: Int, y: Int): Either[BusinessError, RGBPixel] = {
    pixelArray.getPixel(x, y) match {
      case Some(pixel) => Right(pixel)
      case None => Left(BusinessError(s"Pixel at ($x, $y) is out of bounds"))
    }
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitRGBImage(this)
  }
}
