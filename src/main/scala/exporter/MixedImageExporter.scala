package exporter

import models.Image.Image

class MixedImageExporter (exporters: List[Exporter[Image]]) extends Exporter[Image] {
  override def output(item: Image): Unit = {
    exporters.foreach(_.output(item))
  }
}
