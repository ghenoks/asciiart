package models.image

import helpers.ASCIIImageHelper
import models.Image.ASCIIImage
import models.Pixel.ASCIIPixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite
import ui.visitor.TestVisitor

class ASCIIImageTest extends AnyFunSuite with ASCIIImageHelper {
  
  test("ASCIIImage should retrieve the correct pixel when within bounds") {

    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    asciiImage.getPixel(0, 0) match {
      case Right(pixel) => assert(pixel.getValue == 'x')
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    asciiImage.getPixel(0, 1) match {
      case Right(pixel) => assert(pixel.getValue == 'y')
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    asciiImage.getPixel(0, 2) match {
      case Right(pixel) => assert(pixel.getValue == 'z')
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("ASCIIImage returns BusinessError when accessing out-of-bounds pixels") {
    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    asciiImage.getPixel(1, 1) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (1, 1) is out of bounds"))
    }
    asciiImage.getPixel(-1, 0) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (-1, 0) is out of bounds"))
    }
    asciiImage.getPixel(1, 0) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (1, 0) is out of bounds"))
    }
  }

  test("ASCIIImage should correctly accept a visitor") {
    val pixels = Vector(Vector(ASCIIPixel('x'), ASCIIPixel('y'), ASCIIPixel('z')))
    val asciiImage = createASCIIImage(pixels)

    val visitor = new TestVisitor()
    val result = asciiImage.accept(visitor)
    assert(result == "Visited ASCIIImage")
  }
}
