package converter

import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.GreyScalePixel
import models.PixelArray

class TestGreyScaleConverter extends GreyScaleConverter[RGBImage] {
  override def convert(image: RGBImage): GreyScaleImage = {

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
