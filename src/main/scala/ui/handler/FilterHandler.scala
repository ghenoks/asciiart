package ui.handler

import filter.ImageFilter
import models.BusinessError
import models.Image.GreyScaleImage

class FilterHandler (filter: ImageFilter[GreyScaleImage], nextHandler: Handler[GreyScaleImage]) extends Handler[GreyScaleImage] {
  override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = {
    filter.applyFilter(item) match {
      case Right(image) => nextHandler.handle(image)
      case Left(error) => Left(error)
    }
  }
}
