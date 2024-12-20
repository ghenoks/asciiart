package ui.handler

import models.BusinessError

/*
 * Represents end of chain of responsibility
 * Always returns Unit -> Success
 */
object EndHandler extends Handler[Any] {
  override def handle(item: Any): Either[BusinessError, Unit] = {Right(())}
}

