package ui.handler

import exporter.{Exporter, ImageExporter}
import models.BusinessError
import models.Image.{ASCIIImage, Image}

/*
 * Handles exporting of ASCIIImage
 * If export was successful then it gives result to nextHandler to handle
 * If it failed returns BusinessError
 * If any of the next handlers fails returns BusinessError
 */

class ExportHandler (exporter: Exporter[Image], nextHandler: Handler[ASCIIImage]) extends Handler[ASCIIImage] {
  override def handle(item: ASCIIImage): Either[BusinessError, Unit] = {
    exporter.output(item) match {
      case Right(()) => nextHandler.handle(item)
      case Left(error) => Left(error)
    }
  }
}
