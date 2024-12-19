package ui.handler

import exporter.{Exporter, ImageExporter}
import models.BusinessError
import models.Image.{ASCIIImage, Image}

class ExportHandler (exporter: Exporter[Image], nextHandler: Handler[ASCIIImage]) extends Handler[ASCIIImage] {
  override def handle(item: ASCIIImage): Either[BusinessError, Unit] = {
    exporter.output(item)
    nextHandler.handle(item) match {
      case Right(()) => Right(())
      case Left(error) => Left(error)
    }
  }
}
