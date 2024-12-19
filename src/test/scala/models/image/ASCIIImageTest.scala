package models.image

import models.Image.ASCIIImage
import models.Pixel.ASCIIPixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite
import ui.visitor.TestVisitor

class ASCIIImageTest extends AnyFunSuite {
  test("ASCIIImage should retrieve the correct pixel when within bounds") {
    val pixels = Vector(Vector(ASCIIPixel('x')))
    val pixelArray = PixelArray(pixels)

    pixelArray match {
      case Right(arr) =>
        val image = ASCIIImage(arr)
        image.getPixel(0, 0) match {
          case Right(pixel) => assert(pixel.getValue == 'x')
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("ASCIIImage returns BusinessError when accessing out-of-bounds pixels") {
    val pixels = Vector(Vector(ASCIIPixel('x')))
    val pixelArray = PixelArray(pixels)

    pixelArray match {
      case Right(arr) =>
        val image = ASCIIImage(arr)
        image.getPixel(1, 1) match {
          case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
          case Left(error) => assert(error == BusinessError(s"Pixel at (1, 1) is out of bounds"))
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("ASCIIImage should correctly accept a visitor") {
    val pixels = Vector(Vector(ASCIIPixel('x')))
    val pixelArray = PixelArray(pixels)

    pixelArray match {
      case Right(arr) =>
        val image = ASCIIImage(arr)
        val visitor = new TestVisitor()
        val result = image.accept(visitor)
        assert(result == "Visited ASCIIImage")
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
