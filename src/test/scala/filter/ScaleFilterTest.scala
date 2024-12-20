package filter

import helpers.GreyImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class ScaleFilterTest extends AnyFunSuite with GreyImageHelper {
  test("Scale filter with scale 4") {
    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(150)
    val pixels = Vector(
      Vector(pixel1, pixel2)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val scaleFilter = new ScaleFilter(4)

    scaleFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 100)
        assert(getGreyValue(filteredImage, 0, 1) == 100)
        assert(getGreyValue(filteredImage, 0, 2) == 150)
        assert(getGreyValue(filteredImage, 0, 3) == 150)
        assert(getGreyValue(filteredImage, 1, 0) == 100)
        assert(getGreyValue(filteredImage, 1, 1) == 100)
        assert(getGreyValue(filteredImage, 1, 2) == 150)
        assert(getGreyValue(filteredImage, 1, 3) == 150)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Scale filter with scale 0.25") {
    val pixel1 = createGreyScalePixel(10)
    val pixel2 = createGreyScalePixel(20)
    val pixel3 = createGreyScalePixel(30)
    val pixel4 = createGreyScalePixel(40)
    val pixel5 = createGreyScalePixel(50)
    val pixel6 = createGreyScalePixel(60)
    val pixel7 = createGreyScalePixel(70)
    val pixel8 = createGreyScalePixel(80)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3, pixel4),
      Vector(pixel5, pixel6, pixel7, pixel8)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val scaleFilter = new ScaleFilter(0.25)

    scaleFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 10)
        assert(getGreyValue(filteredImage, 0, 1) == 30)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Scale filter with scale 1 (no change)") {
    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(150)
    val pixels = Vector(
      Vector(pixel1, pixel2)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val scaleFilter = new ScaleFilter(1)

    scaleFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 100)
        assert(getGreyValue(filteredImage, 0, 1) == 150)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Scale filter with invalid scale") {
    val pixels = Vector(Vector(createGreyScalePixel(100)))
    val greyScaleImage = createGreyScaleImage(pixels)

    val scaleFilter = new ScaleFilter(2)

    scaleFilter.applyFilter(greyScaleImage) match {
      case Right(_) => fail("Expected error for invalid scale")
      case Left(error) => assert(error.message == "Value for Scale Filter invalid")
    }
  }

  test("Scaling of an empty image") {
    val pixels = Vector(Vector())
    val greyScaleImage = createGreyScaleImage(pixels)

    val invertFilter = new ScaleFilter(1)

    invertFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        result.getPixel(0, 0) match {
          case Right(pixel) => fail(s"Unexpected error: Pixel should be out of bounds")
          case Left(error) => assert(error == BusinessError("Pixel at (0, 0) is out of bounds"))
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
