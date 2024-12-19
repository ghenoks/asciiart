package models

import models.Pixel.Pixel
import org.scalatest.funsuite.AnyFunSuite

class PixelArrayTest extends AnyFunSuite {
  case class MockPixel(value: Int) extends Pixel

  // PixelArrays used in tests
  private val pixelArray = PixelArray(Vector(
    Vector(MockPixel(1), MockPixel(2), MockPixel(3)),
    Vector(MockPixel(4), MockPixel(5), MockPixel(6)),
    Vector(MockPixel(7), MockPixel(8), MockPixel(9))
  ))

  private val emptyPixelArray = PixelArray(Vector.empty[Vector[MockPixel]])

  test("getPixel returns correct pixel within bounds") {
    pixelArray match {
      case Right(arr) =>
        assert (arr.getPixel (0, 0).contains (MockPixel (1) ) )
        assert (arr.getPixel (0, 1).contains (MockPixel (2) ) )
        assert (arr.getPixel (0, 2).contains (MockPixel (3) ) )
        assert (arr.getPixel (1, 0).contains (MockPixel (4) ) )
        assert (arr.getPixel (1, 1).contains (MockPixel (5) ) )
        assert (arr.getPixel (1, 2).contains (MockPixel (6) ) )
        assert (arr.getPixel (2, 0).contains (MockPixel (7) ) )
        assert (arr.getPixel (2, 1).contains (MockPixel (8) ) )
        assert (arr.getPixel (2, 2).contains (MockPixel (9) ) )

      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("getPixel returns None out of bounds") {
    pixelArray match {
      case Right(arr) =>
        assert(arr.getPixel(3, 0).isEmpty)
        assert(arr.getPixel(3, 3).isEmpty)
        assert(arr.getPixel(-1, 0).isEmpty)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    emptyPixelArray match {
      case Right(arr) => assert(arr.getPixel(0, 0).isEmpty)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
  test("Height is correct") {
    pixelArray match {
      case Right(arr) => assert(arr.getHeight == 3)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    emptyPixelArray match {
      case Right(arr) => assert(arr.getHeight == 0)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Width is correct") {
    pixelArray match {
      case Right(arr) => assert(arr.getWidth == 3)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
    emptyPixelArray match {
      case Right(arr) => assert(arr.getWidth == 0)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("PixelArray returns BusinessError for non-rectangular data") {
    val errorPixelArray = PixelArray(Vector(
      Vector(MockPixel(1), MockPixel(2), MockPixel(3)),
      Vector(MockPixel(4)
      )))

    errorPixelArray match {
      case Right(arr) => fail(s"Unexpected error: apply in PixelArray supposed to return BusinessError")
      case Left(error) => assert(error == BusinessError("All rows in the PixelArray must have the same number of columns"))
    }
  }
}
