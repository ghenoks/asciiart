package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class ScaleFilter(val scale: Float) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    scale match {
      case 4 => scale4(image)
      case 0.25 => scale025(image)
    }
  }

  private def scale4(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height * 2, width * 2)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            val value = pixel.getValue

            GreyScalePixel(value) match {
              case Right(pixel) =>
                pixels(x * 2)(y * 2) = pixel
                pixels(x * 2 + 1)(y * 2) = pixel
                pixels(x * 2)(y * 2 + 1) = pixel
                pixels(x * 2 + 1)(y * 2 + 1) = pixel
              case Left(error) => throw IllegalArgumentException(error.message)
            }
          case Left(error) => throw IllegalArgumentException(error.message)
        }

      }
    }

    val vector = pixels.map(_.toVector).toVector
    val pixelArray = PixelArray[GreyScalePixel](vector)

    pixelArray match {
      case Right(arr) => GreyScaleImage(arr)
      case Left(error) => throw IllegalArgumentException(error.message)
    }
  }

  private def scale025(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height/2, width/2)

    for (x <- 0 until height/2) {
      for (y <- 0 until width/2) {
        image.getPixel(x * 2, y * 2) match {
          case Right(pixel) =>
            val value = pixel.getValue

            GreyScalePixel(value) match {
              case Right(pixel) => pixels(x)(y) = pixel
              case Left(error) => throw IllegalArgumentException(error.message)
            }
          case Left(error) => throw IllegalArgumentException(error.message)
        }
      }
    }

    val vector = pixels.map(_.toVector).toVector
    val pixelArray = PixelArray[GreyScalePixel](vector)

    pixelArray match {
      case Right(arr) => GreyScaleImage(arr)
      case Left(error) => throw IllegalArgumentException(error.message)
    }
  }
}
