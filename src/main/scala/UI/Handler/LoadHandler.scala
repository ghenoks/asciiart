package UI.Handler

import Loader.ImageLoader
import Models.Image.{Image, RGBImage}

class LoadHandler(loader: ImageLoader, nextHandler: Handler[Image]) extends Handler[Any] {
  override def handle(item: Any): Unit = {
    val image = loader.load()
    nextHandler.handle(image)
  }
}
