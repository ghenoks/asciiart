package loader

import helpers.errorFlagValidator.RGBErrorFlagValidator
import models.Image.RGBImage
import models.Pixel.RGBPixel
import models.BusinessError

import scala.util.Random

/*
 * Generates random RGB-Image
 * Size of Image is between 100x100 - 500x500
 */
class RandomImageLoader extends ImageLoader with RGBErrorFlagValidator {
  override def load(): Either[BusinessError, RGBImage]  = {
    val random = new Random()

    val width = random.between(100, 500)
    val height = random.between(100, 500)
    
    // used for detecting errors in loops 
    var errorFlag: Option[String] = None

    val pixels = Array.ofDim[RGBPixel](height, width)

    for (x <- 0 until height if errorFlag.isEmpty) {
      for (y <- 0 until width if errorFlag.isEmpty) {
        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)
        RGBPixel(red, green, blue) match {
          case Right(pixel) => pixels(x)(y) = pixel
          case Left(error) => errorFlag = Some(error.message)
        }
      }
    }

    validateErrorFlag(pixels, errorFlag)
  }
}
