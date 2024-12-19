package ui.view

trait View {
  def showErrorMessage(message: String): Unit
  def showSuccessMessage(): Unit
}
