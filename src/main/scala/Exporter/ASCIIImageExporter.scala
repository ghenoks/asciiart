package Exporter

import Models.Image.Image
import Models.Image.visitor.ImageVisitor

class ASCIIImageExporter(textExporter: TextExporter, visitor: ImageVisitor[String]) extends Exporter[Image] {
  override def output(item: Image): Unit = {
    textExporter.output(item.accept(visitor))
    textExporter.close()
  }
}
