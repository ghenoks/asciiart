package exporter

import models.BusinessError
import models.Image.Image
import ui.visitor.ImageToStringVisitor

/*
 * Exports image in the form of text somewhere
 * @param textExporter - used for exporting the image in text format
 */
class ImageExporter(textExporter: TextExporter) extends Exporter[Image] {
  override def output(item: Image): Either[BusinessError, Unit] = {
    val visitor = ImageToStringVisitor()
    val content = item.accept(visitor)

    textExporter.output(content) match {
      case Right(()) => Right(())
      case Left(error) => Left(error)
    }
  }
}
