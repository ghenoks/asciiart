package exporter

import models.Image.Image
import ui.visitor.ImageToStringVisitor

class ImageExporter(textExporter: TextExporter) extends Exporter[Image] {
  override def output(item: Image): Unit = {
    val visitor = ImageToStringVisitor()
    val content = item.accept(visitor)
    textExporter.output(content)
  }
}
