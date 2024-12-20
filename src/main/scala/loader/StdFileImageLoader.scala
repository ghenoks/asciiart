package loader

import helpers.errorFlagValidator.RGBErrorFlagValidator
import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.BusinessError

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.collection.mutable.ArrayBuffer

/*
 * Loads files that are supported by ImageIO.read(File)
 * Returns BusinessError if loading fails
 */
class StdFileImageLoader(val fileName: String) extends ImageLoader with RGBErrorFlagValidator {
  override def load(): Either[BusinessError, RGBImage] = {

    try {
      val image: BufferedImage = ImageIO.read(new File(fileName))
      if (image == null)
        Left(BusinessError("Not a supported file format."))

      else {

        val height: Int = image.getHeight
        val width: Int = image.getWidth

        val pixels = ArrayBuffer[ArrayBuffer[RGBPixel]]()
        var errorFlag: Option[String] = None

        for (y <- 0 until height if errorFlag.isEmpty) {
          val pixelLine = ArrayBuffer[RGBPixel]()
          for (x <- 0 until width if errorFlag.isEmpty) {
            val color: Int = image.getRGB(x, y)

            val red = (color >> 16) & 0xFF
            val green = (color >> 8) & 0xFF
            val blue = color & 0xFF

            RGBPixel(red, green, blue) match {
              case Right(pixel) => pixelLine.addOne(pixel)
              case Left(error) => errorFlag = Some(error.message)
            }
          }
          pixels.addOne(pixelLine)
        }
        validateErrorFlag(pixels.map(_.toArray).toArray, errorFlag)
      }
    }
    catch {
      case _: javax.imageio.IIOException =>
        Left(BusinessError("File does not exist."))
      case _: Exception =>
        Left(BusinessError("An error occurred while reading the file"))
    }
  }
}