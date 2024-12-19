package ui.view

class ConsoleView extends View {
  override def showErrorMessage(message: String): Unit = println(message)
}
