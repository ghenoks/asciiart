package exporter

import models.Image.Image
import models.Image.visitor.ImageVisitor
import ui.ImageToStringVisitor

class ASCIIImageExporter(textExporter: TextExporter) extends Exporter[Image] {
  override def output(item: Image): Unit = {
    val visitor = ImageToStringVisitor()
    val content = item.accept(visitor)
    textExporter.output(content)
  }
}
