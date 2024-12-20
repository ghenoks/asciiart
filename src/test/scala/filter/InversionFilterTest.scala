package filter

import helpers.GreyImageHelper
import models.{Axis, BusinessError}
import org.scalatest.funsuite.AnyFunSuite

class InversionFilterTest extends AnyFunSuite with GreyImageHelper {
  test("Inversion of grey-scale image") {
    val pixel1 = createGreyScalePixel(0)
    val pixel2 = createGreyScalePixel(255)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(100)
    val pixel5 = createGreyScalePixel(55)
    val pixel6 = createGreyScalePixel(50)
    val pixels = Vector(
      Vector(pixel1, pixel2, pixel3),
      Vector(pixel4, pixel5, pixel6))
    val greyScaleImage = createGreyScaleImage(pixels)

    val inversionFilter = new InversionFilter()

    inversionFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 255)
        assert(getGreyValue(filteredImage, 0, 1) == 0)
        assert(getGreyValue(filteredImage, 0, 2) == 105)
        assert(getGreyValue(filteredImage, 1, 0) == 155)
        assert(getGreyValue(filteredImage, 1, 1) == 200)
        assert(getGreyValue(filteredImage, 1, 2) == 205)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Inversion of empty image") {
    val pixels = Vector(Vector())
    val greyScaleImage = createGreyScaleImage(pixels)

    val invertFilter = new InversionFilter

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
