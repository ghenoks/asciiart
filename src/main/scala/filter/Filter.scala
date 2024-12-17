package filter

trait Filter[T] {
  def applyFilter(image: T): T
}
