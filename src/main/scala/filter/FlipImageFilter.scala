package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.{Axis, PixelArray}

class FlipImageFilter(val axis: Axis.Axis) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {
    axis match {
      case Axis.X => flipHorizontal(image)
      case Axis.Y => flipVertical(image)
    }
  }

  private def flipHorizontal(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth

    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            GreyScalePixel(pixel.getValue) match {
              case Right(tmpPixel) => pixels(height - 1 - x)(y) = tmpPixel
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

  private def flipVertical(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth

    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            GreyScalePixel(pixel.getValue) match {
              case Right(tmpPixel) => pixels(x)(width - 1 - y) = tmpPixel
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
