package Converter

import Models.Image.{ASCIIImage, Image}
import Models.Pixel.Pixel
import Models.conversionTable.ConversionTable

trait ASCIIConverter[T <: Image, P <: Pixel] extends Converter[T, ASCIIImage] {
  def table: ConversionTable[P]
}
