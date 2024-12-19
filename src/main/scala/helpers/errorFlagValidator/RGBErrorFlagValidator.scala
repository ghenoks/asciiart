package helpers.errorFlagValidator

import helpers.errorFlagValidator.ErrorFlagValidator
import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.{BusinessError, PixelArray}

trait RGBErrorFlagValidator extends ErrorFlagValidator[RGBPixel, RGBImage] {
  override def validateErrorFlag(pixels: Array[Array[RGBPixel]], errorFlag: Option[String]): Either[BusinessError, RGBImage] = {
    errorFlag match {
      case Some(message) => Left(BusinessError(message))
      case None =>
        val vector = pixels.map(_.toVector).toVector
        val pixelArray = PixelArray[RGBPixel](vector)

        pixelArray match {
          case Right(arr) => Right(RGBImage(arr))
          case Left(error) => Left(error)
        }
    }
  }
}
