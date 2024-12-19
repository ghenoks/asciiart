package filter

import models.BusinessError

trait Filter[T] {
  def applyFilter(image: T): Either[BusinessError, T]
}
