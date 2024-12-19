package ui.argumentParser

import models.{Argument, BusinessError}

trait ArgumentParser {
  def parseArgs(): Either[BusinessError, List[Argument]]
}
