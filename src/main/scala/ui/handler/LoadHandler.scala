package ui.handler

import loader.ImageLoader
import models.Image.{GreyScaleImage, Image, RGBImage}

class LoadHandler(loader: ImageLoader, nextHandler: Handler[Image]) extends Handler[Any] {
  override def handle(item: Any): Unit = {
    val image = loader.load()
    nextHandler.handle(image)
  }
}
