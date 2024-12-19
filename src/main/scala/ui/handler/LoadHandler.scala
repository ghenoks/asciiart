package ui.handler

import loader.ImageLoader
import models.BusinessError
import models.Image.Image

class LoadHandler(loader: ImageLoader, nextHandler: Handler[Image]) extends Handler[Any] {
  override def handle(item: Any): Either[BusinessError, Unit] = {
    loader.load() match {
      case Right(image) =>
        nextHandler.handle(image) match {
          case Right(()) => Right(())
          case Left(error) => Left(error)
        }
      case Left(error) => Left(error)
    }
  }
}
