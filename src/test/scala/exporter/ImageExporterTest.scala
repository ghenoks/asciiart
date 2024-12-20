package exporter

import helpers.ASCIIImageHelper
import models.{BusinessError, PixelArray}
import models.Image.{ASCIIImage, Image}
import models.Pixel.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar
import ui.visitor.ImageToStringVisitor

class ImageExporterTest extends AnyFunSuite with ASCIIImageHelper {
  
  test("Image exported successfully") {
    val textExporter = new StdOutputExporter()
    val visitor = ImageToStringVisitor()
    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val exporter = new ImageExporter(textExporter)
    
    val result = exporter.output(asciiImage)
    assert(result == Right(()))
  }
}
