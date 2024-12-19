package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class TestImageFilter extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    GreyScalePixel(0) match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) => GreyScaleImage(arr)
          case Left(error) => throw IllegalArgumentException(error.message)
        }
      case Left(error) => throw IllegalArgumentException(error.message)
    }

  }
}
