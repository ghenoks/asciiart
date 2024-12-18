package converter

import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.{ASCIIPixel, GreyScalePixel}
import models.PixelArray
import models.conversionTable.ConversionTable

import scala.collection.mutable.ArrayBuffer

case class GreyScaleToASCIIConverter(table: ConversionTable[GreyScalePixel]) extends ASCIIConverter[GreyScaleImage, GreyScalePixel] {
  override def convert(image: GreyScaleImage): ASCIIImage = {

    val pixels = ArrayBuffer[ArrayBuffer[ASCIIPixel]]()

    val height: Int = image.getHeight
    val width: Int = image.getWidth

    for (x <- 0 until height) {
      val pixelLine = ArrayBuffer[ASCIIPixel]()
      for (y <- 0 until width) {
        val greyPixel: GreyScalePixel = image.getPixel(x, y)

        val symbol = table.getSymbol(greyPixel)
        pixelLine.addOne(ASCIIPixel(symbol))
      }
      pixels.addOne(pixelLine)
    }

    val vector = pixels.map(_.toVector).toVector
    ASCIIImage(PixelArray[ASCIIPixel](vector))
  }
}
