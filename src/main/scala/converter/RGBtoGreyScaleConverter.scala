package converter

import helpers.errorFlagValidator.GreyErrorFlagValidator
import models.Image.{GreyScaleImage, RGBImage}
import models.Pixel.{GreyScalePixel, RGBPixel}
import models.BusinessError

import scala.collection.mutable.ArrayBuffer

class RGBtoGreyScaleConverter extends GreyScaleConverter[RGBImage] with GreyErrorFlagValidator {
  override def convert(image: RGBImage): Either[BusinessError, GreyScaleImage] = {

    val pixels = ArrayBuffer[ArrayBuffer[GreyScalePixel]]()

    val height: Int = image.getHeight
    val width: Int = image.getWidth

    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      val pixelLine = ArrayBuffer[GreyScalePixel]()
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(rgbPixel) =>
            val red = rgbPixel.getRed
            val green = rgbPixel.getGreen
            val blue = rgbPixel.getBlue

            val value: Int = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
            GreyScalePixel(value) match {
              case Right(pixel) => pixelLine.addOne(pixel)
              case Left(error) => errorFlag = Some(error.message)
            }
          case Left(error) => errorFlag = Some(error.message)
        }
      }
      pixels.addOne(pixelLine)
    }

    validateErrorFlag(pixels.map(_.toArray).toArray, errorFlag)
  }
}
