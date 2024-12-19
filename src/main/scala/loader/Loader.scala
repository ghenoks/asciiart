package loader

import models.BusinessError

trait Loader[+T] {
  def load() : Either[BusinessError, T]
}
