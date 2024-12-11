package Models.Image

import Models.Pixel.ASCIIPixel

case class ASCIIImage (pixels: Vector[Vector[ASCIIPixel]]) extends Image {
  override def getPixel(x: Int, y: Int): ASCIIPixel = {
    pixels(x)(y)
  }

  def toASCIIString: String = {
    pixels.map(row => row.map(_.getValue).mkString).mkString("\n")
  }
}
