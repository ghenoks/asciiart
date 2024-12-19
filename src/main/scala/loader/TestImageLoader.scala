package loader

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.PixelArray

class TestImageLoader extends ImageLoader {
  override def load(): RGBImage = {
    val pixels = Vector(Vector(RGBPixel(0, 0, 0)))
    val pixelArray = PixelArray(pixels)
    RGBImage(pixelArray)
  }
}
