package Exporter

import Models.Image.Image
import Models.Image.visitor.ImageVisitor
import UI.ImageToStringVisitor

class ASCIIImageExporter(textExporter: TextExporter) extends Exporter[Image] {
  override def output(item: Image): Unit = {
    val visitor = ImageToStringVisitor()
    val content = item.accept(visitor)
    textExporter.output(content)
    textExporter.close()
  }
}
