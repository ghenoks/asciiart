package Filter

trait Filter[T] {
  def applyFilter(image: T): T
}
