package ui.moduleGetter

trait ModuleGetter[T] {
  def getModules: Either[String, T]
}
