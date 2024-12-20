package models.conversionTable

import models.Pixel.GreyScalePixel

/*
 * Conversion Table used to map values onto symbols from table
 * Divides the numbers from 0-255 equally into the symbols of table
 */
class LinearConversionTable (val table: String) extends ConversionTable[GreyScalePixel] {

  private def interval: Double = if (table.nonEmpty) 256 / table.length else 1

  override def getSymbol(value: GreyScalePixel): Char = {
    if (table.isEmpty || table.length > 256) ' '
    else {
      val index: Int = Math.min((value.getValue / interval).toInt, table.length - 1)
      table.charAt(index)
    }
  }
}
