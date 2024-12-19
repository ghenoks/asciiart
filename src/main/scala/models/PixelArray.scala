package models

import models.Pixel.Pixel

class PixelArray[+T <: Pixel] private (private val pixels: Vector[Vector[T]]) {

  private val height: Int = pixels.length
  private val width: Int = if (pixels.nonEmpty) pixels.head.length else 0

  def getPixel (x: Int, y: Int): Option[T] = {
    if (x >= 0 && x < height && y >= 0 && y < width) {
      Some(pixels(x)(y))
    }
    else None
  }

  def getHeight: Int = height
  def getWidth: Int = width
}

object PixelArray {
  def apply[T <: Pixel](pixels: Vector[Vector[T]]): Either[BusinessError, PixelArray[T]] = {
    if (pixels.isEmpty || pixels.forall(_.length == pixels.head.length)) {
      Right(new PixelArray[T](pixels))
    } else {
      Left(BusinessError("All rows in the PixelArray must have the same number of columns"))
    }
  }
}
