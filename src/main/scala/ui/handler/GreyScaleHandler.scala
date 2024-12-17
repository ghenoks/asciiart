package ui.handler

import converter.GreyScaleConverter
import models.Image.{GreyScaleImage, Image, RGBImage}

class GreyScaleHandler (converter: GreyScaleConverter[RGBImage], nextHandler: Handler[GreyScaleImage]) extends Handler[RGBImage] {
  override def handle(item: RGBImage): Unit = {
    val image = converter.convert(item)
    nextHandler.handle(image)
  }
}
