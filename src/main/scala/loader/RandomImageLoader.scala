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
      RGBPixel(red, green, blue)
    }

    val vector = pixels.map(_.toVector).toVector
    RGBImage(PixelArray[RGBPixel](vector))
  }
}
