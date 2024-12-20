package ui.argumentParser

import models.{Argument, BusinessError}

/*
 * Used to parse arguments
 */
trait ArgumentParser {
  def parseArgs(): Either[BusinessError, List[Argument]]
}
