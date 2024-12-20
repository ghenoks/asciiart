package exporter

import helpers.ASCIIImageHelper
import models.Image.ASCIIImage
import models.Pixel.ASCIIPixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite

class MixedImageExporterTest extends AnyFunSuite with ASCIIImageHelper {

  test("All exporters succeeded") {
    val exporters = List(new TestImageExporter, new TestImageExporter)
    val mixedExporter = new MixedImageExporter(exporters)

    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val result = mixedExporter.output(asciiImage)

    assert(result == Right(()))
  }

  test("One exporter fails") {
    val exporters = List(new TestImageExporter, new TestImageExporter2)
    val mixedExporter = new MixedImageExporter(exporters)

    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val result = mixedExporter.output(asciiImage)

    assert(result == Left(BusinessError("Export failed.")))
  }

  test("Empty list of exporters") {
    val mixedExporter = new MixedImageExporter(List())

    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val result = mixedExporter.output(asciiImage)

    assert(result == Right(()))
  }
}
