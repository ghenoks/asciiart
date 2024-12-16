package UI.Handler

trait Handler[-T] {
  def handle(item: T): Unit
}
