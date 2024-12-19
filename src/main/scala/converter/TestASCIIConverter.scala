package converter

import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}
import models.PixelArray
import models.conversionTable.ConversionTable

case class TestASCIIConverter (table: ConversionTable[GreyScalePixel]) extends ASCIIConverter[GreyScaleImage, GreyScalePixel] {
  override def convert(image: GreyScaleImage): ASCIIImage = {
    val pixels = Vector(Vector(ASCIIPixel('x')))
    val pixelArray = PixelArray(pixels)

    pixelArray match {
      case Right(arr) => ASCIIImage(arr)
      case Left(error) => throw IllegalArgumentException(error.message)
    }

  }
}
