package loader

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.PixelArray
import scala.util.Random

class RandomImageLoader extends ImageLoader {
  override def load(): RGBImage = {
    val random = new Random()

    val width = random.between(100, 500)
    val height = random.between(100, 500)

    val pixels = Array.fill(height, width) {
      val red = random.nextInt(256)
      val green = random.nextInt(256)
      val blue = random.nextInt(256)
      RGBPixel(red, green, blue) match {
        case Right(pixel) => pixel
        case Left(error) => throw new IllegalArgumentException(s"Failed to create pixel due to error: ${error.message}")
      }
    }  

    val vector = pixels.map(_.toVector).toVector
    val pixelArray = PixelArray[RGBPixel](vector)

    pixelArray match {
      case Right(arr) => RGBImage(arr)
      case Left(error) => throw IllegalArgumentException(error.message)
    }
  }
}
