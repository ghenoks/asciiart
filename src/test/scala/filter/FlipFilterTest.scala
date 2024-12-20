package filter

import helpers.GreyImageHelper
import models.{Axis, BusinessError}
import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import org.scalatest.funsuite.AnyFunSuite

class FlipFilterTest extends AnyFunSuite with GreyImageHelper {
  test("Flip image horizontally") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixel5 = createGreyScalePixel(5)
    val pixel6 = createGreyScalePixel(6)
    val pixel7 = createGreyScalePixel(7)
    val pixel8 = createGreyScalePixel(8)
    val pixel9 = createGreyScalePixel(9)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6),
      Vector(pixel7, pixel8, pixel9))
    val greyScaleImage = createGreyScaleImage(pixels)

    val flipFilter = FlipImageFilter(Axis.X)

    flipFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 7)
        assert(getGreyValue(result, 0, 1) == 8)
        assert(getGreyValue(result, 0, 2) == 9)
        assert(getGreyValue(result, 1, 0) == 4)
        assert(getGreyValue(result, 1, 1) == 5)
        assert(getGreyValue(result, 1, 2) == 6)
        assert(getGreyValue(result, 2, 0) == 1)
        assert(getGreyValue(result, 2, 1) == 2)
        assert(getGreyValue(result, 2, 2) == 3)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Flip image vertically") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixel5 = createGreyScalePixel(5)
    val pixel6 = createGreyScalePixel(6)
    val pixel7 = createGreyScalePixel(7)
    val pixel8 = createGreyScalePixel(8)
    val pixel9 = createGreyScalePixel(9)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6),
      Vector(pixel7, pixel8, pixel9))
    val greyScaleImage = createGreyScaleImage(pixels)

    val flipFilter = FlipImageFilter(Axis.Y)

    flipFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 3)
        assert(getGreyValue(result, 0, 1) == 2)
        assert(getGreyValue(result, 0, 2) == 1)
        assert(getGreyValue(result, 1, 0) == 6)
        assert(getGreyValue(result, 1, 1) == 5)
        assert(getGreyValue(result, 1, 2) == 4)
        assert(getGreyValue(result, 2, 0) == 9)
        assert(getGreyValue(result, 2, 1) == 8)
        assert(getGreyValue(result, 2, 2) == 7)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Flip empty image vertically") {
    val pixels = Vector(Vector())
    val greyScaleImage = createGreyScaleImage(pixels)

    val flipFilter = new FlipImageFilter(Axis.Y)

    flipFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        result.getPixel(0, 0) match {
          case Right(pixel) => fail(s"Unexpected error: Pixel should be out of bounds")
          case Left(error) => assert(error == BusinessError("Pixel at (0, 0) is out of bounds"))
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Flip empty image horizontally") {

    val pixels = Vector(Vector())
    val greyScaleImage = createGreyScaleImage(pixels)

    val flipFilter = new FlipImageFilter(Axis.X)

    flipFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        result.getPixel(0, 0) match {
          case Right(pixel) => fail(s"Unexpected error: Pixel should be out of bounds")
          case Left(error) => assert(error == BusinessError("Pixel at (0, 0) is out of bounds"))
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
