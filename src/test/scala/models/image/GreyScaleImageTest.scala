package models.image

import helpers.GreyImageHelper
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite
import ui.visitor.TestVisitor

class GreyScaleImageTest extends AnyFunSuite with GreyImageHelper {
  
  test("GreyScaleImage should retrieve the correct pixel when within bounds") {

    val pixel1 = createGreyScalePixel(0)
    val pixel2 = createGreyScalePixel(127)
    val pixel3 = createGreyScalePixel(255)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val greyScaleImage = createGreyScaleImage(pixels)

    greyScaleImage.getPixel(0, 0) match {
      case Right(pixel) => assert(pixel.getValue == 0)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    greyScaleImage.getPixel(0, 1) match {
      case Right(pixel) => assert(pixel.getValue == 127)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    greyScaleImage.getPixel(0, 2) match {
      case Right(pixel) => assert(pixel.getValue == 255)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("GreyScaleImage returns BusinessError when accessing out-of-bounds pixels") {

    val pixel1 = createGreyScalePixel(0)
    val pixel2 = createGreyScalePixel(127)
    val pixel3 = createGreyScalePixel(255)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val greyScaleImage = createGreyScaleImage(pixels)

    greyScaleImage.getPixel(1, 1) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (1, 1) is out of bounds"))
    }
    greyScaleImage.getPixel(1, 0) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (1, 0) is out of bounds"))
    }
    greyScaleImage.getPixel(-1, 0) match {
      case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
      case Left(error) => assert(error == BusinessError(s"Pixel at (-1, 0) is out of bounds"))
    }
  }

  test("GreyScaleImage should correctly accept a visitor") {

    val pixel1 = createGreyScalePixel(0)
    val pixel2 = createGreyScalePixel(127)
    val pixel3 = createGreyScalePixel(255)
    val pixels = Vector(Vector(pixel1, pixel2, pixel3))
    val greyScaleImage = createGreyScaleImage(pixels)

    val visitor = new TestVisitor()
    val result = greyScaleImage.accept(visitor)
    assert(result == "Visited GreyScaleImage")
  }
}
