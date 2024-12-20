package ui.visitor

import helpers.{ASCIIImageHelper, GreyImageHelper, RGBImageHelper}
import models.Image.RGBImage
import models.Pixel.ASCIIPixel
import org.scalatest.funsuite.AnyFunSuite

class ImageToStringVisitorTest extends AnyFunSuite with ASCIIImageHelper with RGBImageHelper with GreyImageHelper {
  test("visitASCIIImage should return correct string representation for ASCIIImage") {
    val pixel1 = ASCIIPixel('A')
    val pixel2 = ASCIIPixel('B')
    val pixel3 = ASCIIPixel('C')
    val pixel4 = ASCIIPixel('D')

    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )

    val asciiImage = createASCIIImage(pixels)
    val visitor = new ImageToStringVisitor

    val result = visitor.visitASCIIImage(asciiImage)
    assert(result == "AB\nCD")
  }

  test("visitRGBImage should return unsupported message") {
    val pixel1 = createRGBPixel(0, 50, 200)
    val pixel2 = createRGBPixel(127, 0, 0)
    val pixel3 = createRGBPixel(255, 1, 2)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val rgbImage = createRGBImage(pixels)

    val visitor = new ImageToStringVisitor
    val result = visitor.visitRGBImage(rgbImage)
    assert(result == "Rendering to string not supported for RGBImage.")
  }

  test("visitGreyScaleImage should return unsupported message") {

    val pixel1 = createGreyScalePixel(0)
    val pixel2 = createGreyScalePixel(127)
    val pixel3 = createGreyScalePixel(255)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val greyScaleImage = createGreyScaleImage(pixels)

    val visitor = new ImageToStringVisitor
    val result = visitor.visitGreyScaleImage(greyScaleImage)
    assert(result == "Rendering to string not supported for GreyScaleImage.")
  }
}
