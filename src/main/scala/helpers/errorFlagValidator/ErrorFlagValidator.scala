package helpers.errorFlagValidator

import models.BusinessError

/*
 * Used to detect if errorFlag found an error or not
 * Returns T if not
 * Returns BusinessError if yes
 */
trait ErrorFlagValidator[S, T] {
  def validateErrorFlag(pixels: Array[Array[S]], errorFlag: Option[String]): Either[BusinessError, T]
}
