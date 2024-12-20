package ui.handler

import models.BusinessError

/*
 * handles item
 * If it was successful then it returns Unit
 * If it failed returns BusinessError
 */
trait Handler[-T] {
  def handle(item: T): Either[BusinessError, Unit]
}
