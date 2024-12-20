package ui.handler

import models.BusinessError
import models.Image.{Image, RGBImage}

/*
 * Checks if Image is RGBImage
 * If it is then it gives result to nextHandler to handle
 * If its not returns BusinessError
 * If any of the next handlers fails returns BusinessError
 */

class ImageToRGBHandler(nextHandler: Handler[RGBImage]) extends Handler[Image] {
  override def handle(item: Image): Either[BusinessError, Unit] = {
    item match {
      case rgbImage: RGBImage => nextHandler.handle(rgbImage)
      case _ => Left(BusinessError("Needed RGBImage, got a different type"))
    }
  }
}
