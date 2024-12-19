package filter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.BusinessError

class InversionFilter extends ImageFilter[GreyScaleImage] with GreyErrorFlagValidator {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {

    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)
    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            GreyScalePixel(255 - pixel.getValue) match {
              case Right(tmpPixel) => pixels(x)(y) = tmpPixel
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }
}
