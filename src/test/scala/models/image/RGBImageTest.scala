package models.image

import helpers.RGBImageHelper
import models.Image.RGBImage
import models.Image.visitor.ImageVisitor
import models.Pixel.RGBPixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite
import ui.visitor.TestVisitor

class RGBImageTest extends AnyFunSuite with RGBImageHelper {

  test("RGBImage should retrieve the correct pixel when within bounds") {
    val pixel1 = createRGBPixel(0, 0, 0)
    val pixel2 = createRGBPixel(127, 255, 100)
    val pixel3 = createRGBPixel(255, 30, 70)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val rgbImage = createRGBImage(pixels)

    rgbImage.getPixel(0, 0) match {
      case Right(pixel) =>
        assert(pixel.getRed == 0)
        assert(pixel.getGreen == 0)
        assert(pixel.getBlue == 0)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    rgbImage.getPixel(0, 1) match {
      case Right(pixel) =>
        assert(pixel.getRed == 127)
        assert(pixel.getGreen == 255)
        assert(pixel.getBlue == 100)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    rgbImage.getPixel(0, 2) match {
      case Right(pixel) =>
        assert(pixel.getRed == 255)
        assert(pixel.getGreen == 30)
        assert(pixel.getBlue == 70)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("RGBImage returns BusinessError when accessing out-of-bounds pixels") {
    val pixel1 = createRGBPixel(0, 0, 0)
    val pixel2 = createRGBPixel(127, 255, 100)
    val pixel3 = createRGBPixel(255, 30, 70)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val rgbImage = createRGBImage(pixels)

    rgbImage.getPixel(1, 1) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (1, 1) is out of bounds"))
    }
    rgbImage.getPixel(0, -1) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (0, -1) is out of bounds"))
    }
    rgbImage.getPixel(1, 0) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (1, 0) is out of bounds"))
    }
  }

  test("RGBImage should correctly accept a visitor") {
    val pixel1 = createRGBPixel(0, 0, 0)
    val pixel2 = createRGBPixel(127, 255, 100)
    val pixel3 = createRGBPixel(255, 30, 70)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val rgbImage = createRGBImage(pixels)

    val visitor = new TestVisitor()
    val result = rgbImage.accept(visitor)
    assert(result == "Visited RGBImage")
  }
}
