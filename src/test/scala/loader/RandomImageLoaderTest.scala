package loader

import org.scalatest.funsuite.AnyFunSuite

class RandomImageLoaderTest extends AnyFunSuite {
  test("RandomImageLoader should load a valid RGBImage with dimensions within range") {
    val loader = new RandomImageLoader

    loader.load() match {
      case Right(image) =>
        val height = image.getHeight
        val width = image.getWidth

        assert(height >= 100 && height <= 500)
        assert(width >= 100 && width <= 500)

        for (x <- 0 until height; y <- 0 until width) {
          val pixel = image.getPixel(x, y)
          assert(pixel.isRight)
        }
      case Left(error) => fail(s"Unexpected error: ${error.message}")
    }
  }

  val loader1 = new RandomImageLoader
  val loader2 = new RandomImageLoader

  (loader1.load(), loader2.load()) match {
    case (Right(image1), Right(image2)) =>

      assert(image1.getHeight >= 100 && image1.getHeight <= 500)
      assert(image1.getWidth >= 100 && image1.getWidth <= 500)
      assert(image2.getHeight >= 100 && image2.getHeight <= 500)
      assert(image2.getWidth >= 100 && image2.getWidth <= 500)

      val areImagesIdentical = {
        if (image1.getHeight != image2.getHeight || image1.getWidth != image2.getWidth) false
        else {
          (0 until image1.getHeight).forall { x =>
            (0 until image1.getWidth).forall { y =>
              image1.getPixel(x, y) == image2.getPixel(x, y)
            }
          }
        }
      }
      assert(!areImagesIdentical)

    case (Left(error1), Left(error2)) =>
      fail(s"Both loaders failed: ${error1.message}, ${error2.message}")
    case (Left(error), _) =>
      fail(s"Loader 1 failed: ${error.message}")
    case (_, Left(error)) =>
      fail(s"Loader 2 failed: ${error.message}")
  }
}
