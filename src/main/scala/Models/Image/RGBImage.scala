package Models.Image

import Models.Pixel.{Pixel, RGBPixel}

case class RGBImage (pixels: Vector[Vector[RGBPixel]]) extends Image {
  override def getPixel(x: Int, y: Int): RGBPixel = {
    pixels (x)(y)
  }
}
