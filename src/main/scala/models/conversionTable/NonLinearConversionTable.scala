package models.conversionTable

import models.Pixel.GreyScalePixel

/*
 * Conversion Table used to map values onto symbols from table
 * Divides the numbers 0-99 to symbol(0) and from 100-255 equally into the rest of the symbols of table
 */
class NonLinearConversionTable(val table: String) extends ConversionTable[GreyScalePixel] {

  private def interval: Int = if (table.length > 1) (256 - 100) / (table.length - 1) else 1

  override def getSymbol(value: GreyScalePixel): Char = {
    if (table.isEmpty || table.length > 256) ' '
    else {
      val greyValue = value.getValue

      if (greyValue < 100) table.charAt(0)
      else {
        val index = (greyValue - 100) / interval + 1
        if (index > 0 && index < table.length)
          table.charAt(index)
        else
          table.charAt(table.length - 1)
      }
    }
  }
}