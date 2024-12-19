package models.conversionTable

import models.Pixel.GreyScalePixel

class NonLinearConversionTable(val table: String) extends ConversionTable[GreyScalePixel] {

  private def interval: Int = if (table.length > 1) (255 - 100) / (table.length - 1) else 1

  override def getSymbol(value: GreyScalePixel): Char = {
    if (table.isEmpty) ' '
    else {
      val greyValue = value.getValue

      if (greyValue <= 100) table.charAt(0)
      else {
        val index = (greyValue - 101) / interval + 1
        if (index > 0 && index < table.length)
          table.charAt(index)
        else
          table.charAt(table.length - 1)
      }
    }
  }
}