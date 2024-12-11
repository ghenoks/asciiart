package Models.Image

import Models.Pixel.Pixel

trait Image {
  def pixels: Vector[Vector[Pixel]]
  private val height: Int = pixels.length
  private val width: Int = {
    if (pixels.nonEmpty) pixels.head.length
    else 0
  }

  def getHeight: Int = height
  def getWidth: Int = width
  def getPixel (x: Int, y: Int): Pixel

  def print(): Unit = {
    println(getHeight)
    println(getWidth)
  }
}
