package converter

import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}
import models.{BusinessError, PixelArray}
import models.conversionTable.ConversionTable

/*
 * GreyScale to ASCII-Converter used for testing
 * Doesn't have any real practical use
 */
case class TestASCIIConverter (table: ConversionTable[GreyScalePixel]) extends ASCIIConverter[GreyScaleImage, GreyScalePixel] {
  override def convert(image: GreyScaleImage): Either[BusinessError, ASCIIImage] = {
    val pixels = Vector(Vector(ASCIIPixel('x')))
    val pixelArray = PixelArray(pixels)

    pixelArray match {
      case Right(arr) => Right(ASCIIImage(arr))
      case Left(error) => Left(error)
    }
  }
}
