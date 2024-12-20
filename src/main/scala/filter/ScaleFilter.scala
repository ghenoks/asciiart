package filter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.BusinessError

/*
 * Scales the GreyScale-Image 4x, 1x or 0.25x of its original size
 * Returns BusinessError if filter fails
 */
class ScaleFilter(val scale: Float) extends ImageFilter[GreyScaleImage] with GreyErrorFlagValidator {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {

    scale match {
      case 4 => scale4(image)
      case 0.25 => scale025(image)
      case 1 => Right(image)
      case _ => Left(BusinessError("Value for Scale Filter invalid"))
    }
  }
  
  /*
   * Scales GreyScale-Image 4x
   */
  private def scale4(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {

    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height * 2, width * 2)
    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            val value = pixel.getValue

            GreyScalePixel(value) match {
              case Right(pixel) =>
                pixels(x * 2)(y * 2) = pixel
                pixels(x * 2 + 1)(y * 2) = pixel
                pixels(x * 2)(y * 2 + 1) = pixel
                pixels(x * 2 + 1)(y * 2 + 1) = pixel
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }

  /*
   * Scales GreyScale-Image 0.25x
   */
  private def scale025(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {

    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height/2, width/2)
    var errorFlag: Option[String] = None

    for (x <- 0 until height/2 if errorFlag.isEmpty) {
      for (y <- 0 until width/2 if errorFlag.isEmpty) {
        image.getPixel(x * 2, y * 2) match {
          case Right(pixel) =>
            val value = pixel.getValue

            GreyScalePixel(value) match {
              case Right(pixel) => pixels(x)(y) = pixel
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }
}
