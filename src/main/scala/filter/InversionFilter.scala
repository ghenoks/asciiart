package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class InversionFilter extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {
    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        image.getPixel(x, y) match {
          case Right(pixel) =>
            GreyScalePixel(255 - pixel.getValue) match {
              case Right(tmpPixel) => pixels(x)(y) = tmpPixel
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
