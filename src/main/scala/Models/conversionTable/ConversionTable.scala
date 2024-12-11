package Models.conversionTable

import Models.Pixel.Pixel

trait ConversionTable[-T <: Pixel] {
  def table: String
  def getSymbol(value: T): Char
}
