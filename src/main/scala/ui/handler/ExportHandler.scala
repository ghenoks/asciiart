package ui.handler

import exporter.{ImageExporter, Exporter}
import models.Image.{ASCIIImage, Image}

class ExportHandler (exporters: Exporter[Image], nextHandler: Handler[ASCIIImage]) extends Handler[ASCIIImage] {
  override def handle(item: ASCIIImage): Unit = {
    exporters.output(item)
    nextHandler.handle(item)
  }
}
