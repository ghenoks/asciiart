package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class RotationFilter(val degrees: Int) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {
    degrees match {
      case 90 => rotate(image, (x, y, width, height) => (y, height - 1 - x))
      case 180 => rotate(image, (x, y, width, height) => (height - 1 - x, width - 1 - y))
      case 270 => rotate(image, (x, y, width, height) => (width - 1 - y, x))
      case 0 => image
      case _ => throw new IllegalArgumentException(s"Unsupported rotation angle: $degrees")
    }
  }

  private def rotate(image: GreyScaleImage, transform: (Int, Int, Int, Int) => (Int, Int)): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth

    val (newHeight, newWidth) = degrees match {
      case 90 | 270 => (width, height)
      case 180 => (height, width)
    }

    val pixels = Array.ofDim[GreyScalePixel](newHeight, newWidth)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            val (newX, newY) = transform(x, y, width, height)

            GreyScalePixel(pixel.getValue) match {
              case Right(tmpPixel) => pixels(newX)(newY) = tmpPixel
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
