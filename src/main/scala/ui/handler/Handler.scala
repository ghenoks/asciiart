package ui.handler

import models.BusinessError

trait Handler[-T] {
  def handle(item: T): Either[BusinessError, Unit]
}
