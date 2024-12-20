package loader

import models.BusinessError

/*
 * Loads RGB-Image from .jpg files
 * Returns BusinessError if file is not .jpg file
 */
class JPGFileImageLoader private (fileName: String) extends StdFileImageLoader(fileName) {

}

object JPGFileImageLoader {
  def apply(fileName: String): Either[BusinessError, JPGFileImageLoader] = {
    if (fileName.endsWith(".jpg")) Right(new JPGFileImageLoader(fileName))
    else Left(BusinessError("JPGFileImageLoader requires .jpg file"))
  }
}
