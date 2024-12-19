package models.conversionTable

import models.Pixel.GreyScalePixel

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
