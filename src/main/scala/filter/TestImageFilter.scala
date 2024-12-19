package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class TestImageFilter extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {
    val pixels = Vector(Vector(GreyScalePixel(0)))
    val pixelArray = PixelArray(pixels)
    GreyScaleImage(pixelArray)
  }
}
