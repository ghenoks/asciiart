package exporter

import models.BusinessError
import models.Image.Image

/*
 * Exporter used only in testing
 * No real practical use
 * Always returns BusinessError - export failed
 */

class TestImageExporter2 extends Exporter[Image] {
  override def output(item: Image): Either[BusinessError, Unit] = {Left(BusinessError("Export failed."))}
}
