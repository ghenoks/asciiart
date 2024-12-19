package ui.handler

import converter.ASCIIConverter
import models.BusinessError
import models.Image.{ASCIIImage, GreyScaleImage}
import models.Pixel.GreyScalePixel

class ASCIIHandler (converter: ASCIIConverter[GreyScaleImage, GreyScalePixel], nextHandler: Handler[ASCIIImage]) extends Handler[GreyScaleImage] {
  override def handle(item: GreyScaleImage): Either[BusinessError, Unit] = {
    converter.convert(item) match {
      case Right(image) => nextHandler.handle(image)
      case Left(error) => Left(error)
    }


  }
}
