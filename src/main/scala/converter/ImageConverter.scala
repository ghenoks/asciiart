package converter

import models.BusinessError
import models.Image.Image

trait ImageConverter[-S <: Image, +T <: Image] extends Converter[S, T] {
  def convert(image: S): Either[BusinessError, T]
}
