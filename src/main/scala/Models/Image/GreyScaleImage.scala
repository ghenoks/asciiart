package Models.Image

import Models.Pixel.GreyScalePixel

case class GreyScaleImage (pixels: Vector[Vector[GreyScalePixel]]) extends Image {
  override def getPixel(x: Int, y: Int): GreyScalePixel = {
    pixels(x)(y)
  }
}
