package models.conversionTable

import models.Pixel.Pixel

trait ConversionTable[-T <: Pixel] {
  def table: String
  def getSymbol(value: T): Char
}
