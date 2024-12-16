package UI.Handler

import Exporter.{ASCIIImageExporter, Exporter}
import Models.Image.{ASCIIImage, Image}

class ExportHandler (exporter: Exporter[Image], nextHandler: Handler[ASCIIImage]) extends Handler[ASCIIImage] {
  override def handle(item: ASCIIImage): Unit = {
    exporter.output(item)
    nextHandler.handle(item)
  }
}
