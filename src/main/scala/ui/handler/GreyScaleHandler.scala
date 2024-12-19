package ui.handler

import converter.GreyScaleConverter
import models.BusinessError
import models.Image.{GreyScaleImage, Image, RGBImage}

class GreyScaleHandler (converter: GreyScaleConverter[RGBImage], nextHandler: Handler[GreyScaleImage]) extends Handler[RGBImage] {
  override def handle(item: RGBImage): Either[BusinessError, Unit] = {
    converter.convert(item) match {
      case Right(image) => nextHandler.handle(image)
      case Left(error) => Left(error)
    }
  }
}
