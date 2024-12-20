package loader

import models.BusinessError

/*
 * Loads something from somewhere
 */
trait Loader[+T] {
  def load() : Either[BusinessError, T]
}
