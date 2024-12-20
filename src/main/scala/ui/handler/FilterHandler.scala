package ui.handler

import filter.ImageFilter
import models.BusinessError
import models.Image.GreyScaleImage

/*
 * Handles filter application on GreyScaleImage
 * If filter was successful then it gives result to nextHandler to handle
 * If it failed returns BusinessError
 * If any of the next handlers fails returns BusinessError
 */

class FilterHandler (filter: ImageFilter[GreyScaleImage], nextHandler: Handler[GreyScaleImage]) extends Handler[GreyScaleImage] {
  override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = {
    filter.applyFilter(item) match {
      case Right(image) => nextHandler.handle(image)
      case Left(error) => Left(error)
    }
  }
}
