package loader

import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.PixelArray

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.util.Random

class MyRandomImageLoader extends ImageLoader {
  override def load(): RGBImage = {
    val random = new Random()
    /*
    // Generate random dimensions with reasonable limits
    val width = random.between(10, 500) // Width between 10 and 500
    val height = random.between(10, 500) // Height between 10 and 500

    // Generate random pixels
    val pixels = Array.fill(height, width) {
      val red = random.nextInt(256) // Random value between 0 and 255
      val green = random.nextInt(256)
      val blue = random.nextInt(256)
      RGBPixel(red, green, blue)
    }

    RGBImage(height, width, pixels)
  }

    val height: Int = image.getHeight()
    val width: Int = image.getWidth()

    val pixels = ArrayBuffer[Array[RGBPixel]]()

    for (y <- 0 until height) {
      val pixelLine = ArrayBuffer[RGBPixel]()
      for (x <- 0 until width) {
        val color: Int = image.getRGB(x, y)

        val red = (color >> 16) & 0xFF
        val green = (color >> 8) & 0xFF
        val blue = color & 0xFF

        pixelLine.addOne(RGBPixel(red, green, blue))
      }
      pixels.addOne(pixelLine.toArray)
    }

    val vector: Vector[Vector[RGBPixel]] = pixels.toArray.map(_.toVector).toVector
    RGBImage(height, width, vector) */

    val pixels = Array[Array[RGBPixel]] ()
    RGBImage(PixelArray[RGBPixel](pixels))
  }
}
