package ui.handler

import loader.ImageLoader
import models.BusinessError
import models.Image.Image

/*
 * Handles loading
 * If loading was successful then it gives result to nextHandler to handle
 * If it failed returns BusinessError
 * If any of the next handlers fails returns BusinessError
 */
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
