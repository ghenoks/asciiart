package filter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.BusinessError

class BrightnessFilter(val value: Int) extends ImageFilter[GreyScaleImage] with GreyErrorFlagValidator{
  override def applyFilter(image: GreyScaleImage): Either[BusinessError, GreyScaleImage] = {

    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)
    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            val greyValue = pixel.getValue
            var newValue: Int = 0
            if (greyValue + value < 0) newValue = 0
            else if (greyValue + value > 255) newValue = 255
            else newValue = greyValue + value

            GreyScalePixel(newValue) match {
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
