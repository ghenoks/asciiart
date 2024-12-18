package ui.argumentParser

import models.Argument

trait ArgumentParser {
  def parseArgs(): Either[String, List[Argument]]
}
