package UI.Handler

import Converter.ASCIIConverter
import Models.Image.{ASCIIImage, GreyScaleImage}
import Models.Pixel.GreyScalePixel

class ASCIIHandler (converter: ASCIIConverter[GreyScaleImage, GreyScalePixel], nextHandler: Handler[ASCIIImage]) extends Handler[GreyScaleImage] {
  override def handle(item: GreyScaleImage): Unit = {
    val image = converter.convert(item)
    nextHandler.handle(image)
  }
}
