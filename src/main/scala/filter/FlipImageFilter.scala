package filter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.{Axis, BusinessError}

class FlipImageFilter(val axis: Axis.Axis) extends ImageFilter[GreyScaleImage] with GreyErrorFlagValidator {
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    axis match {
      case Axis.X => flipHorizontal(image)
      case Axis.Y => flipVertical(image)
    }
  }

  private def flipHorizontal(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)
    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            GreyScalePixel(pixel.getValue) match {
              case Right(tmpPixel) => pixels(height - 1 - x)(y) = tmpPixel
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }

  private def flipVertical(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {
    
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)
    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            GreyScalePixel(pixel.getValue) match {
              case Right(tmpPixel) => pixels(x)(width - 1 - y) = tmpPixel
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }
}
