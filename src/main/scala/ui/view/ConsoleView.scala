package ui.view

class ConsoleView extends View {
  /*
   * to check if message was shown yet
   */
  var successMessageShown = false
  var errorMessageShown = false

  /*
   * Prints error message into console
   */
  override def showErrorMessage(message: String): Unit = {
    println(s"Error detected: $message")
    errorMessageShown = true
  }
  
  /*
   * Prints success message into console
   */
  override def showSuccessMessage(): Unit = {
    println("Conversion was SUCCESSFUL :)")
    successMessageShown = true
  }
}
