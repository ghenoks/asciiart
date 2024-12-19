package helpers.errorFlagValidator

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.{BusinessError, PixelArray}

trait GreyErrorFlagValidator extends ErrorFlagValidator[GreyScalePixel, GreyScaleImage] {
  override def validateErrorFlag(pixels: Array[Array[GreyScalePixel]], errorFlag: Option[String]): Either[BusinessError, GreyScaleImage] = {
    errorFlag match {
      case Some(message) => Left(BusinessError(message))
      case None =>
        val vector = pixels.map(_.toVector).toVector
        val pixelArray = PixelArray[GreyScalePixel](vector)

        pixelArray match {
          case Right(arr) => Right(GreyScaleImage(arr))
          case Left(error) => Left(error)
        }
    }
  }
}
