package loader

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.PixelArray

class TestImageLoader extends ImageLoader {
  override def load(): RGBImage = {
    val pixelCheck = RGBPixel(0, 0, 0)
    
    pixelCheck match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) => RGBImage(arr)
          case Left(error) => throw IllegalArgumentException(error.message)
        }
      case Left(error) => throw IllegalArgumentException(error.message)
    }
  }
}
