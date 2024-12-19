package loader

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.{BusinessError, PixelArray}

class TestImageLoader extends ImageLoader {
  override def load(): Either[BusinessError, RGBImage] = {
    val pixelCheck = RGBPixel(0, 0, 0)
    
    pixelCheck match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) => Right(RGBImage(arr))
          case Left(error) => Left(error)
        }
      case Left(error) => Left(error)
    }
  }
}
