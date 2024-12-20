package models.conversionTable

import models.Pixel.Pixel

/*
 * USed to convert value into ASCII symbol
 * Table holds possible symbols for conversion
 * getSymbol returns mapped symbol from value
 */
trait ConversionTable[-T <: Pixel] {
  def table: String
  def getSymbol(value: T): Char
}
