package filter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.BusinessError

/*
 * Inverts the Grey color values of pixels in GreyScale-Image
 * Returns BusinessError if it fails
 */
class InversionFilter extends ImageFilter[GreyScaleImage] with GreyErrorFlagValidator {
  /*
   * Calculates new value for each pixel in GreyScale-Image
   */
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
