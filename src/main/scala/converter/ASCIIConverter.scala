package converter

import models.Image.{ASCIIImage, Image}
import models.Pixel.Pixel
import models.conversionTable.ConversionTable

/*
 * Used to convert Image to ASCII-Image
 * Table is used to map values of Image to ASCII symbols
 */
trait ASCIIConverter[T <: Image, P <: Pixel] extends ImageConverter[T, ASCIIImage] {
  def table: ConversionTable[P]
}
