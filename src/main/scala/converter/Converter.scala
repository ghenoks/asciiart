package converter

import models.BusinessError

trait Converter[-S, +T] {
  def convert(image: S): Either[BusinessError, T]
}
