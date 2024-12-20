package filter

import helpers.GreyImageHelper
import models.BusinessError
import org.scalatest.funsuite.AnyFunSuite

class RotationFilterTest extends AnyFunSuite with GreyImageHelper {

  test("Rotate image 90 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(90)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 3)
        assert(getGreyValue(filteredImage, 0, 1) == 1)
        assert(getGreyValue(filteredImage, 1, 0) == 4)
        assert(getGreyValue(filteredImage, 1, 1) == 2)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image -270 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(-270)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 3)
        assert(getGreyValue(filteredImage, 0, 1) == 1)
        assert(getGreyValue(filteredImage, 1, 0) == 4)
        assert(getGreyValue(filteredImage, 1, 1) == 2)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image 180 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(180)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 4)
        assert(getGreyValue(filteredImage, 0, 1) == 3)
        assert(getGreyValue(filteredImage, 1, 0) == 2)
        assert(getGreyValue(filteredImage, 1, 1) == 1)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image -180 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(-180)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 4)
        assert(getGreyValue(filteredImage, 0, 1) == 3)
        assert(getGreyValue(filteredImage, 1, 0) == 2)
        assert(getGreyValue(filteredImage, 1, 1) == 1)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image 270 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(270)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 2)
        assert(getGreyValue(filteredImage, 0, 1) == 4)
        assert(getGreyValue(filteredImage, 1, 0) == 1)
        assert(getGreyValue(filteredImage, 1, 1) == 3)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image -90 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(270)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 2)
        assert(getGreyValue(filteredImage, 0, 1) == 4)
        assert(getGreyValue(filteredImage, 1, 0) == 1)
        assert(getGreyValue(filteredImage, 1, 1) == 3)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image 0 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(0)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 1)
        assert(getGreyValue(filteredImage, 0, 1) == 2)
        assert(getGreyValue(filteredImage, 1, 0) == 3)
        assert(getGreyValue(filteredImage, 1, 1) == 4)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image 360 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(360)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 1)
        assert(getGreyValue(filteredImage, 0, 1) == 2)
        assert(getGreyValue(filteredImage, 1, 0) == 3)
        assert(getGreyValue(filteredImage, 1, 1) == 4)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image -360 degrees") {
    val pixel1 = createGreyScalePixel(1)
    val pixel2 = createGreyScalePixel(2)
    val pixel3 = createGreyScalePixel(3)
    val pixel4 = createGreyScalePixel(4)
    val pixels = Vector(
      Vector(pixel1, pixel2),
      Vector(pixel3, pixel4)
    )
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(-360)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(filteredImage) =>
        assert(getGreyValue(filteredImage, 0, 0) == 1)
        assert(getGreyValue(filteredImage, 0, 1) == 2)
        assert(getGreyValue(filteredImage, 1, 0) == 3)
        assert(getGreyValue(filteredImage, 1, 1) == 4)
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  test("Rotate image by invalid angle") {
    val pixel = createGreyScalePixel(1)
    val pixels = Vector(Vector(pixel))
    val greyScaleImage = createGreyScaleImage(pixels)

    val rotationFilter = new RotationFilter(45)

    rotationFilter.applyFilter(greyScaleImage) match {
      case Right(_) => fail("Expected error for invalid rotation angle")
      case Left(error) => assert(error.message == "Unsupported rotation angle: 45")
    }
  }

  test("Scaling of an empty image") {
    val pixels = Vector(Vector())
    val greyScaleImage = createGreyScaleImage(pixels)

    val invertFilter = new RotationFilter(90)

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
