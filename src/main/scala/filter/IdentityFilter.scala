package filter

import models.BusinessError

/*
 * Returns the same item it gets
 */
trait IdentityFilter[T] extends Filter[T] {
  override def applyFilter(image: T): Either[BusinessError, T] = Right(image)
}
