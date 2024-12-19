package filter

import models.BusinessError

trait IdentityFilter[T] extends Filter[T] {
  override def applyFilter(image: T): Either[BusinessError, T] = Right(image)
}
