package ui.moduleGetter

import models.BusinessError

/*
 * Gets modules
 */
trait ModuleGetter[T] {
  def getModules: Either[BusinessError, T]
}
