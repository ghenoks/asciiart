package ui.handler

import exporter.{ASCIIImageExporter, Exporter}
import models.Image.{ASCIIImage, Image}

class ExportHandler (exporters: List[Exporter[Image]], nextHandler: Handler[ASCIIImage]) extends Handler[ASCIIImage] {
  override def handle(item: ASCIIImage): Unit = {
    exporters.foreach(_.output(item))
    nextHandler.handle(item)
  }
}
