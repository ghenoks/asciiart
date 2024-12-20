package loader

import models.BusinessError

/*
 * Loads RGB-Image from .png files
 * Returns BusinessError if file is not .png file
 */

class PNGFileImageLoader private (fileName: String) extends StdFileImageLoader(fileName) {

}

object PNGFileImageLoader {
  def apply(fileName: String): Either[BusinessError, PNGFileImageLoader] = {
    if (fileName.endsWith(".png")) Right(new PNGFileImageLoader(fileName))
    else Left(BusinessError("PNGFileImageLoader requires .png file"))
  }
}