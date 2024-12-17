package loader

trait Loader[+T] {
  def load() : T
}
