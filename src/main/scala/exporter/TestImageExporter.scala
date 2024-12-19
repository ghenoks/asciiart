package exporter

import models.BusinessError
import models.Image.Image

class TestImageExporter extends Exporter[Image] {
  override def output(item: Image): Either[BusinessError, Unit] = {Right(())}
}
