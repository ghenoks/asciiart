package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.ASCIIPixel
import models.{BusinessError, PixelArray}

case class ASCIIImage (pixelArray: PixelArray[ASCIIPixel]) extends Image {
  override def getPixel(x: Int, y: Int): Either[BusinessError, ASCIIPixel] = {
    pixelArray.getPixel(x, y) match {
      case Some(pixel) => Right(pixel)
      case None => Left(BusinessError(s"Pixel at ($x, $y) is out of bounds"))
    }
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitASCIIImage(this)
  }
}
