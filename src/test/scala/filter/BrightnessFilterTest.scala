package filter

import helpers.GreyImageHelper
import org.scalatest.funsuite.AnyFunSuite

class BrightnessFilterTest extends AnyFunSuite with GreyImageHelper {
  test("Increase brightness within normal range") {
    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixel5 = createGreyScalePixel(90)
    val pixel6 = createGreyScalePixel(70)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6))
    val greyScaleImage = createGreyScaleImage(pixels)

    val brightnessFilter = new BrightnessFilter(+50)

    brightnessFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 150)
        assert(getGreyValue(result, 0, 1) == 250)
        assert(getGreyValue(result, 0, 2) == 200)
        assert(getGreyValue(result, 1, 0) == 100)
        assert(getGreyValue(result, 1, 1) == 140)
        assert(getGreyValue(result, 1, 2) == 120)

      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Decrease brightness within normal range") {
    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixel5 = createGreyScalePixel(90)
    val pixel6 = createGreyScalePixel(70)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6))
    val greyScaleImage = createGreyScaleImage(pixels)

    val brightnessFilter = new BrightnessFilter(-50)

    brightnessFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 50)
        assert(getGreyValue(result, 0, 1) == 150)
        assert(getGreyValue(result, 0, 2) == 100)
        assert(getGreyValue(result, 1, 0) == 0)
        assert(getGreyValue(result, 1, 1) == 40)
        assert(getGreyValue(result, 1, 2) == 20)

      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Increase brightness over 255") {
    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixel5 = createGreyScalePixel(90)
    val pixel6 = createGreyScalePixel(70)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6))
    val greyScaleImage = createGreyScaleImage(pixels)

    val brightnessFilter = new BrightnessFilter(300)

    brightnessFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 255)
        assert(getGreyValue(result, 0, 1) == 255)
        assert(getGreyValue(result, 0, 2) == 255)
        assert(getGreyValue(result, 1, 0) == 255)
        assert(getGreyValue(result, 1, 1) == 255)
        assert(getGreyValue(result, 1, 2) == 255)

      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Decrease brightness under 0") {
    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixel5 = createGreyScalePixel(90)
    val pixel6 = createGreyScalePixel(70)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6))
    val greyScaleImage = createGreyScaleImage(pixels)

    val brightnessFilter = new BrightnessFilter(-300)

    brightnessFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 0)
        assert(getGreyValue(result, 0, 1) == 0)
        assert(getGreyValue(result, 0, 2) == 0)
        assert(getGreyValue(result, 1, 0) == 0)
        assert(getGreyValue(result, 1, 1) == 0)
        assert(getGreyValue(result, 1, 2) == 0)

      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
