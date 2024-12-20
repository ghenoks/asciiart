package filter

import helpers.GreyImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class GreyImageIdentityFilterTest extends AnyFunSuite with GreyImageHelper {
  test("Identity Filter returns the same image") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixel5 = createGreyScalePixel(5)
    val pixel6 = createGreyScalePixel(6)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6))
    val greyScaleImage = createGreyScaleImage(pixels)

    val identityFilter = new GreyImageIdentityFilter()

    identityFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        assert(getGreyValue(result, 0, 0) == 1)
        assert(getGreyValue(result, 0, 1) == 2)
        assert(getGreyValue(result, 0, 2) == 3)
        assert(getGreyValue(result, 1, 0) == 4)
        assert(getGreyValue(result, 1, 1) == 5)
        assert(getGreyValue(result, 1, 2) == 6)

      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Empty image returns empty image") {

    val pixels = Vector(Vector())
    val greyScaleImage = createGreyScaleImage(pixels)

    val identityFilter = new GreyImageIdentityFilter()

    identityFilter.applyFilter(greyScaleImage) match {
      case Right(result) =>
        result.getPixel(0, 0) match {
          case Right(pixel) => fail(s"Unexpected error: Pixel should be out of bounds")
          case Left(error) => assert(error == BusinessError("Pixel at (0, 0) is out of bounds"))
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}