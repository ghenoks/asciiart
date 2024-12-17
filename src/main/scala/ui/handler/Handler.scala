package ui.handler

trait Handler[-T] {
  def handle(item: T): Unit
}
