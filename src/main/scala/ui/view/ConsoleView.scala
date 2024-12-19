package ui.view

class ConsoleView extends View {
  override def showErrorMessage(message: String): Unit = println(s"Error detected: $message")

  override def showSuccessMessage(): Unit = println("Conversion was SUCCESSFUL :)")
}
