package ui.handler

import org.scalatest.funsuite.AnyFunSuite

class EndHandlerTest extends AnyFunSuite {
  test("EndHandler should always return success") {
    val result = EndHandler.handle(())

    assert(result == Right(()))
  }
}
