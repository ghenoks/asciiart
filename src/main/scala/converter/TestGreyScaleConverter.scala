package converter

import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.GreyScalePixel
import models.PixelArray

class TestGreyScaleConverter extends GreyScaleConverter[RGBImage] {
  override def convert(image: RGBImage): GreyScaleImage = {
    val pixels = Vector(Vector(GreyScalePixel(0)))
    val pixelArray = PixelArray(pixels)
    GreyScaleImage(pixelArray)
  }
}
