package models

import models.Pixel.Pixel

class PixelArray[+T <: Pixel] (private val pixels: Vector[Vector[T]]) {

  require(pixels.isEmpty || pixels.forall(_.length == pixels.head.length), "All rows in the PixelArray must have the same number of columns")

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
