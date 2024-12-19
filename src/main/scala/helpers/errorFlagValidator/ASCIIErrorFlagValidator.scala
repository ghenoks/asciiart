package helpers.errorFlagValidator

import models.Image.{ASCIIImage, RGBImage}
import models.Pixel.{ASCIIPixel, RGBPixel}
import models.{BusinessError, PixelArray}

trait ASCIIErrorFlagValidator extends ErrorFlagValidator[ASCIIPixel, ASCIIImage] {
  override def validateErrorFlag(pixels: Array[Array[ASCIIPixel]], errorFlag: Option[String]): Either[BusinessError, ASCIIImage] = {
    errorFlag match {
      case Some(message) => Left(BusinessError(message))
      case None =>
        val vector = pixels.map(_.toVector).toVector
        val pixelArray = PixelArray[ASCIIPixel](vector)

        pixelArray match {
          case Right(arr) => Right(ASCIIImage(arr))
          case Left(error) => Left(error)
        }
    }
  }
}
