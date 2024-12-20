package helpers.errorFlagValidator

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.{BusinessError, PixelArray}

/*
 * Validates if errorFlag found error
 * If not then returns RGB-Image
 * If yes then it returns BusinessError
 */
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
