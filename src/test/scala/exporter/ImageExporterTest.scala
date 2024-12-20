package exporter

import models.{BusinessError, PixelArray}
import models.Image.{ASCIIImage, Image}
import models.Pixel.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar
import ui.visitor.ImageToStringVisitor

class ImageExporterTest extends AnyFunSuite {

  def createASCIIImage(pixels: Vector[Vector[ASCIIPixel]]): ASCIIImage = {
    val pixelArray = PixelArray(pixels)
    pixelArray match {
      case Right(arr) => ASCIIImage(arr)
      case Left(error) => throw new RuntimeException(s"Failed to create GreyScaleImage: ${error.message}")
    }
  }

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
