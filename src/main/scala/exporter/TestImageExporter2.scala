package exporter

import models.BusinessError
import models.Image.Image

class TestImageExporter2 extends Exporter[Image] {
  override def output(item: Image): Either[BusinessError, Unit] = {Left(BusinessError("Export failed."))}
}
