package converter

import models.Image.{ASCIIImage, Image}
import models.Pixel.Pixel
import models.conversionTable.ConversionTable

trait ASCIIConverter[T <: Image, P <: Pixel] extends ImageConverter[T, ASCIIImage] {
  def table: ConversionTable[P]
}
