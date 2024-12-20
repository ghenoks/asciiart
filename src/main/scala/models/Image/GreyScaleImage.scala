package models.Image

import models.Image.visitor.ImageVisitor
import models.Pixel.GreyScalePixel
import models.{BusinessError, PixelArray}

/*
 * Represents an Image made out of pixels with GreyScale values
 * Pixels are stored in pixelArray
 * getPixel returns pixel in position [x,y] of pixelArray or BusinessError if its out of bounds
 * accept accepts visitor
 */
case class GreyScaleImage (pixelArray: PixelArray[GreyScalePixel]) extends Image {
  override def getPixel(x: Int, y: Int): Either[BusinessError, GreyScalePixel] = {
    pixelArray.getPixel(x, y) match {
      case Some(pixel) => Right(pixel)
      case None => Left(BusinessError(s"Pixel at ($x, $y) is out of bounds"))
    }
  }

  override def accept[T](visitor: ImageVisitor[T]): T = {
    visitor.visitGreyScaleImage(this)
  }
}
