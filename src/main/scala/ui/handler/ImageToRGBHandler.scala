package ui.handler

import models.Image.{Image, RGBImage}

class ImageToRGBHandler(nextHandler: Handler[RGBImage]) extends Handler[Image] {
  override def handle(item: Image): Unit = {
    item match {
      case rgbImage: RGBImage => nextHandler.handle(rgbImage)
      case _ => throw new IllegalArgumentException("Expected RGBImage but got another type of Image.")
    }
  }
}
