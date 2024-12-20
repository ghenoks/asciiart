package exporter

import models.BusinessError
import models.Image.Image

/*
 * Exporter used only in testing
 * No real practical use
 * Always returns Unit - export was successful
 */
class TestImageExporter extends Exporter[Image] {
  override def output(item: Image): Either[BusinessError, Unit] = {Right(())}
}
