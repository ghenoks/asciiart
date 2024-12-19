package models

import org.scalatest.funsuite.AnyFunSuite

class BusinessErrorTest extends AnyFunSuite {
  test("BusinessError is correctly initialized with a message") {
    val errorMessage = "An error occurred"
    val businessError = BusinessError(errorMessage)

    assert(businessError.message == errorMessage)
  }

  test("Two BusinessError instances with the same message are equal") {
    val errorMessage = "An error occurred"
    val error1 = BusinessError(errorMessage)
    val error2 = BusinessError(errorMessage)

    assert(error1 == error2)
  }

  test("Two BusinessError instances with different messages are not equal") {
    val error1 = BusinessError("Error 1")
    val error2 = BusinessError("Error 2")
    
    assert(error1 != error2)
  }
}
