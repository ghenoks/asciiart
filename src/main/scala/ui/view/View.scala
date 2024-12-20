package ui.view


trait View {
  /*
   * Shows Error message
   */
  def showErrorMessage(message: String): Unit

  /*
   * Shows Success message
   */
  def showSuccessMessage(): Unit
}
