package filter

import models.BusinessError

/*
 * Applies filter on something
 */
trait Filter[T] {
  def applyFilter(image: T): Either[BusinessError, T]
}
