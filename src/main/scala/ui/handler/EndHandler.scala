package ui.handler

import models.BusinessError
import ui.handler.Handler

object EndHandler extends Handler[Any] {
  override def handle(item: Any): Either[BusinessError, Unit] = {Right(())}
}

