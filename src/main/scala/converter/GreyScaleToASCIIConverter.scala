package converter

import helpers.errorFlagValidator.ASCIIErrorFlagValidator
import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}
import models.BusinessError
import models.conversionTable.ConversionTable

import scala.collection.mutable.ArrayBuffer

/*
 * Converts GreyScaleImage to ASCII-Image
 * Conversion Table maps values from GreyScale to ASCII symbols
 */
class GreyScaleToASCIIConverter(val table: ConversionTable[GreyScalePixel]) extends ASCIIConverter[GreyScaleImage, GreyScalePixel] with ASCIIErrorFlagValidator {
  
  /*
   * For each Pixel in GreyScaleImage finds ASCII symbol and adds to ASCII-Image
   */
  override def convert(image: GreyScaleImage): Either[BusinessError, ASCIIImage] = {

    val pixels = ArrayBuffer[ArrayBuffer[ASCIIPixel]]()

    val height: Int = image.getHeight
    val width: Int = image.getWidth
    
    // used for detecting errors in loops
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
