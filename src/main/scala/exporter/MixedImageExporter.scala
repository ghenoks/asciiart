package exporter

import models.BusinessError
import models.Image.Image

/*
 * Used for holding multiple exporters at once and running them
 * Returns BusinessError if any of the exporters fail
 */
class MixedImageExporter (exporters: List[Exporter[Image]]) extends Exporter[Image] {
  override def output(item: Image): Either[BusinessError, Unit] = {
    var errorFlag: Option[BusinessError] = None
    var i = 0

    while (i < exporters.length && errorFlag.isEmpty) {
      exporters(i).output(item) match {
        case Left(error) =>
          errorFlag = Some(error)
        case Right(_) => ()
      }
      i += 1
    }

    errorFlag match {
      case Some(err) => Left(err)
      case None => Right(())
    }
  }
}
