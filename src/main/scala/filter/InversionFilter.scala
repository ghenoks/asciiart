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
        val pixel = image.getPixel(x, y)
        pixels(x)(y) = GreyScalePixel(255 - pixel.getValue)
      }
    }

    //val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(PixelArray[GreyScalePixel](pixels))
  }
}
