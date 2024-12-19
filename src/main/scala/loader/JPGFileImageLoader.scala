package loader

import models.BusinessError

class JPGFileImageLoader (fileName: String) extends StdFileImageLoader(fileName) {

}

object JPGFileImageLoader {
  def apply(fileName: String): Either[BusinessError, JPGFileImageLoader] = {
    if (fileName.endsWith(".jpg")) Right(new JPGFileImageLoader(fileName))
    else Left(BusinessError("JPGFileImageLoader requires .jpg file"))
  }
}
