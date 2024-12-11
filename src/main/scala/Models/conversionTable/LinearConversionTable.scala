package Models.conversionTable

import Models.Pixel.GreyScalePixel

class LinearConversionTable (val table: String) extends ConversionTable[GreyScalePixel] {

  private def interval: Int = 255/table.length
  override def getSymbol(value: GreyScalePixel): Char = {

    val index: Int = value.getValue/interval
    if (index >= 0 && index < table.length) {
      table.charAt(index)
    }
    else table.charAt(0)
  }
}
