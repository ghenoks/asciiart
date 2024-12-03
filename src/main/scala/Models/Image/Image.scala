package Models.Image

import Models.Pixel.Pixel

case class Image (private val height: Int, private val width: Int, private val pixels: Vector[Vector[Pixel]] ) {
  def getHeight: Int = height
  def getWidth: Int = width
}
