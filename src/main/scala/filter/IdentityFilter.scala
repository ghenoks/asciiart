package filter

trait IdentityFilter[T] extends Filter[T] {
  override def applyFilter(image: T): T = image
}
