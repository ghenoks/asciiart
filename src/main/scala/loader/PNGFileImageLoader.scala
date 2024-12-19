package loader

import models.BusinessError

class PNGFileImageLoader(fileName: String) extends StdFileImageLoader(fileName) {

}

object PNGFileImageLoader {
  def apply(fileName: String): Either[BusinessError, PNGFileImageLoader] = {
    if (fileName.endsWith(".png")) Right(new PNGFileImageLoader(fileName))
    else Left(BusinessError("JPGFileImageLoader requires .jpg file"))
  }
}