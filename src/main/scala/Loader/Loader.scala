package Loader

trait Loader[T] {
  def load() : T
}
