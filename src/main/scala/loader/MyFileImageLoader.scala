package loader

import models.Image.RGBImage
import models.Pixel.RGBPixel

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.ArrayBuffer

class MyFileImageLoader(val fileName: String) extends ImageLoader {
  override def load(): RGBImage = {

    val image: BufferedImage = ImageIO.read(new File(fileName))
    if (image == null)
      throw new Exception("Not a supported file format.")

    val height: Int = image.getHeight
    val width: Int = image.getWidth

    val pixels = ArrayBuffer[ArrayBuffer[RGBPixel]]()

    for (y <- 0 until height) {
      val pixelLine = ArrayBuffer[RGBPixel]()
      for (x <- 0 until width) {
        val color: Int = image.getRGB(x, y)

        val red = (color >> 16) & 0xFF
        val green = (color >> 8) & 0xFF
        val blue = color & 0xFF

        pixelLine.addOne(RGBPixel(red, green, blue))
      }
      pixels.addOne(pixelLine)
    }

    val vector: Vector[Vector[RGBPixel]] = pixels.map(_.toVector).toVector
    RGBImage(vector)
  }
}
