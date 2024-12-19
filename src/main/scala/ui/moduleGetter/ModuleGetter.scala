package ui.moduleGetter

import models.BusinessError

trait ModuleGetter[T] {
  def getModules: Either[BusinessError, T]
}
