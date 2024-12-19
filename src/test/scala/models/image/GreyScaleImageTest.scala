package models.image

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.{BusinessError, PixelArray}
import org.scalatest.funsuite.AnyFunSuite
import ui.visitor.TestVisitor

class GreyScaleImageTest extends AnyFunSuite {
  test("GreyScaleImage should retrieve the correct pixel when within bounds") {
    val result = GreyScalePixel(0)

    result match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) =>

            val image = GreyScaleImage(arr)
            image.getPixel(0, 0) match {
              case Right(pixel) => assert(pixel.getValue == 0)
              case Left(error) => fail(s"Unexpected error: ${error.message}")
            }
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("GreyScaleImage returns BusinessError when accessing out-of-bounds pixels") {
    val result = GreyScalePixel(0)

    result match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) =>
            val image = GreyScaleImage(arr)

            image.getPixel(1, 1) match {
              case Right(pixel) => fail(s"Unexpected error: getPixel should return BusinessError")
              case Left(error) => assert(error == BusinessError(s"Pixel at (1, 1) is out of bounds"))
            }
          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("GreyScaleImage should correctly accept a visitor") {
    val result = GreyScalePixel(0)

    result match {
      case Right(pixel) =>
        val pixels = Vector(Vector(pixel))
        val pixelArray = PixelArray(pixels)

        pixelArray match {
          case Right(arr) =>
            val image = GreyScaleImage(arr)
            val visitor = new TestVisitor()
            val result = image.accept(visitor)
            assert(result == "Visited GreyScaleImage")

          case Left(error) => fail(s"Unexpected error: ${error.message}")
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
