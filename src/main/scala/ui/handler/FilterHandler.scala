package ui.handler

import filter.ImageFilter
import models.Image.GreyScaleImage

class FilterHandler (filter: ImageFilter[GreyScaleImage], nextHandler: Handler[GreyScaleImage]) extends Handler[GreyScaleImage] {
  override def handle(item: GreyScaleImage): Unit = {
    val image = filter.applyFilter(item)
    nextHandler.handle(image)
  }
}
