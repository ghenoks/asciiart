package models

import models.Pixel.Pixel

class PixelArray[+T <: Pixel] (private val pixels: Vector[Vector[T]]) {

  private val height: Int = pixels.length
  private val width: Int = if (pixels.nonEmpty) pixels.head.length else 0

  def getPixel (x: Int, y: Int): T = {
    // add check if in bounds
    pixels (x)(y)
  }

  def getHeight: Int = height
  def getWidth: Int = width
}
