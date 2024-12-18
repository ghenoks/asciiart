package filter

import models.Image.GreyScaleImage
import models.Pixel.GreyScalePixel
import models.PixelArray

class BrightnessFilter(val value: Int) extends ImageFilter[GreyScaleImage] {
  override def applyFilter(image: GreyScaleImage): GreyScaleImage = {

    val height = image.getHeight
    val width = image.getWidth
    val pixels = Array.ofDim[GreyScalePixel](height, width)

    for (x <- 0 until height) {
      for (y <- 0 until width) {
        val greyValue = image.getPixel(x, y).getValue

        var newValue: Int = 0
        if (greyValue + value < 0) newValue = 0
        else if (greyValue + value > 255) newValue = 255
        else newValue = greyValue + value

        pixels(x)(y) = GreyScalePixel(newValue)
      }
    }

    val vector = pixels.map(_.toVector).toVector
    GreyScaleImage(PixelArray[GreyScalePixel](vector))
  }
}
