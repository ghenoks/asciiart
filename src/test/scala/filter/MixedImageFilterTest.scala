package filter

import helpers.GreyImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class MixedImageFilterTest extends AnyFunSuite with GreyImageHelper {
  test("All filters succeed") {
    val filters = List(new GreyImageIdentityFilter, new GreyImageIdentityFilter)
    val mixedFilter = new MixedImageFilter(filters)

    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4))
    val greyScaleImage = createGreyScaleImage(pixels)

    mixedFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 100)
        assert(getGreyValue(filteredImage, 0, 1) == 200)
        assert(getGreyValue(filteredImage, 1, 0) == 150)
        assert(getGreyValue(filteredImage, 1, 1) == 50)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("One exporter fails") {
    val filters = List(new GreyImageIdentityFilter, new FailImageFilter)
    val mixedFilter = new MixedImageFilter(filters)

    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4))
    val greyScaleImage = createGreyScaleImage(pixels)

    val result = mixedFilter.applyFilter(greyScaleImage)

    assert(result == Left(BusinessError("Filter failed.")))
  }

  test("Empty list of exporters") {
    val mixedFilter = new MixedImageFilter(List())

    val pixel1 = createGreyScalePixel(100)
    val pixel2 = createGreyScalePixel(200)
    val pixel3 = createGreyScalePixel(150)
    val pixel4 = createGreyScalePixel(50)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4))
    val greyScaleImage = createGreyScaleImage(pixels)

    val result = mixedFilter.applyFilter(greyScaleImage)

    mixedFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 100)
        assert(getGreyValue(filteredImage, 0, 1) == 200)
        assert(getGreyValue(filteredImage, 1, 0) == 150)
        assert(getGreyValue(filteredImage, 1, 1) == 50)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }
}
