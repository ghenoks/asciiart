package exporter

import models.Image.Image

class TestImageExporter extends Exporter[Image] {
  override def output(item: Image): Unit = {}
}
