package ui.handler

import models.BusinessError
import models.Image.{Image, RGBImage}

class ImageToRGBHandler(nextHandler: Handler[RGBImage]) extends Handler[Image] {
  override def handle(item: Image): Either[BusinessError, Unit] = {
    item match {
      case rgbImage: RGBImage => nextHandler.handle(rgbImage)
      case _ => Left(BusinessError("Needed RGBImage, got a different type"))
    }
  }
}
