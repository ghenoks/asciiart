package models

import models.Pixel.Pixel
import org.scalatest.funsuite.AnyFunSuite

class PixelArrayTest extends AnyFunSuite {
  case class MockPixel(value: Int) extends Pixel

  val pixelArray = new PixelArray(Vector(
    Vector(MockPixel(1), MockPixel(2), MockPixel(3)),
    Vector(MockPixel(4), MockPixel(5), MockPixel(6)),
    Vector(MockPixel(7), MockPixel(8), MockPixel(9))
  ))

  val emptyPixelArray = new PixelArray(Vector.empty[Vector[MockPixel]])

  test("getPixel returns correct pixel within bounds") {
    assert(pixelArray.getPixel(0, 0).contains(MockPixel(1)))
    assert(pixelArray.getPixel(1, 1).contains(MockPixel(5)))
    assert(pixelArray.getPixel(2, 2).contains(MockPixel(9)))
  }

  test("getPixel returns None out of bounds") {
    assert(pixelArray.getPixel(3, 0).isEmpty)
    assert(pixelArray.getPixel(3, 3).isEmpty)
    assert(pixelArray.getPixel(-1, 0).isEmpty)
    assert(emptyPixelArray.getPixel(0, 0).isEmpty)
  }

  test("Height is correct") {
    assert(pixelArray.getHeight == 3)
    assert(emptyPixelArray.getHeight == 0)
  }

  test("Width is correct") {
    assert(pixelArray.getWidth == 3)
    assert(emptyPixelArray.getWidth == 0)
  }

  test("PixelArray throws exception for non-rectangular data") {
    val exception = intercept[IllegalArgumentException] {
      new PixelArray(Vector(
        Vector(MockPixel(1), MockPixel(2)),
        Vector(MockPixel(3))
      ))
    }
    assert(exception.getMessage == "requirement failed: All rows in the PixelArray must have the same number of columns")
  }
}
