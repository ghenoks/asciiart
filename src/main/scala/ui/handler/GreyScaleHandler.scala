package ui.handler

import converter.GreyScaleConverter
import models.BusinessError
import models.Image.{GreyScaleImage, Image, RGBImage}

/*
 * Handles RGB to GreyScale-Image conversion
 * If conversion was successful then it gives result to nextHandler to handle
 * If it failed returns BusinessError
 * If any of the next handlers fails returns BusinessError
 */
class GreyScaleHandler (converter: GreyScaleConverter[RGBImage], nextHandler: Handler[GreyScaleImage]) extends Handler[RGBImage] {
  override def handle(item: RGBImage): Either[BusinessError, Unit] = {
    converter.convert(item) match {
      case Right(image) => nextHandler.handle(image)
      case Left(error) => Left(error)
    }
  }
}
