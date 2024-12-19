package converter

import helpers.errorFlagValidator.ASCIIErrorFlagValidator
import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}
import models.BusinessError
import models.conversionTable.ConversionTable

import scala.collection.mutable.ArrayBuffer

case class GreyScaleToASCIIConverter(table: ConversionTable[GreyScalePixel]) extends ASCIIConverter[GreyScaleImage, GreyScalePixel] with ASCIIErrorFlagValidator {
  override def convert(image: GreyScaleImage): Either[BusinessError, ASCIIImage] = {

    val pixels = ArrayBuffer[ArrayBuffer[ASCIIPixel]]()

    val height: Int = image.getHeight
    val width: Int = image.getWidth

    var errorFlag: Option[String] = None

    for (x <- 0 until height if errorFlag.isEmpty) {
      val pixelLine = ArrayBuffer[ASCIIPixel]()
      for (y <- 0 until width if errorFlag.isEmpty) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            val symbol = table.getSymbol(pixel)
            pixelLine.addOne(ASCIIPixel(symbol))
          case Left(error) => errorFlag = Some(error.message)
        }
      }
      pixels.addOne(pixelLine)
    }

    validateErrorFlag(pixels.map(_.toArray).toArray, errorFlag)
  }
}
