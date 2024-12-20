package converter

import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.GreyScalePixel
import models.{BusinessError, PixelArray}

/*
 * RGB to GreyScale-Converter used for testing
 * Doesn't have any real practical use
 */

class TestGreyScaleConverter extends GreyScaleConverter[RGBImage] {
  override def convert(image: RGBImage): Either[BusinessError, GreyScaleImage] = {

    GreyScalePixel(0) match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) => Right(GreyScaleImage(arr))
          case Left(error) => Left(error)
        }
      case Left(error) => Left(error)
    }

  }
}
