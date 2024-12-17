package ui.handler

import converter.ASCIIConverter
import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.GreyScalePixel

class ASCIIHandler (converter: ASCIIConverter[GreyScaleImage, GreyScalePixel], nextHandler: Handler[ASCIIImage]) extends Handler[GreyScaleImage] {
  override def handle(item: GreyScaleImage): Unit = {
    val image = converter.convert(item)
    nextHandler.handle(image)
  }
}
