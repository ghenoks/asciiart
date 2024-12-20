package filter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.BusinessError

/*
 * Rotates GreyScale-Image by degrees dividable by 90
 * Returns BusinessError if filter fails
 */
class RotationFilter(val degrees: Int) extends ImageFilter[GreyScaleImage] with GreyErrorFlagValidator {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    degrees match {
      case 90 | -270 => rotate(image, (x, y, width, height) => (y, height - 1 - x))
      case 180 | -180 => rotate(image, (x, y, width, height) => (height - 1 - x, width - 1 - y))
      case 270 | -90 => rotate(image, (x, y, width, height) => (width - 1 - y, x))
      case 0 | -360 | 360 => Right(image)
      case _ => Left(BusinessError(s"Unsupported rotation angle: $degrees"))
    }
  }

  private def rotate(image: GreyScaleImage, transform: (Int, Int, Int, Int) => (Int, Int)): Either[BusinessError, GreyScaleImage] = {
    val height = image.getHeight
    val width = image.getWidth

    val (newHeight, newWidth) = degrees match {
      case 90 | 270 | -270 | -90 => (width, height)
      case 180 | -180 => (height, width)
    }

    val pixels = Array.ofDim[GreyScalePixel](newHeight, newWidth)

    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            val (newX, newY) = transform(x, y, width, height)

            GreyScalePixel(pixel.getValue) match {
              case Right(tmpPixel) => pixels(newX)(newY) = tmpPixel
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }
}
