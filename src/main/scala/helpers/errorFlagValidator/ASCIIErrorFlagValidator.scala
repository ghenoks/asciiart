package helpers.errorFlagValidator

import models.Image.ASCIIImage
import models.Pixel.ASCIIPixel
import models.{BusinessError, PixelArray}

/*
 * Validates if errorFlag found error
 * If not then returns ASCII-Image
 * If yes then it returns BusinessError
 */
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
