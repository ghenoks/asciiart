package helpers.errorFlagValidator

import models.BusinessError

trait ErrorFlagValidator[S, T] {
  def validateErrorFlag(pixels: Array[Array[S]], errorFlag: Option[String]): Either[BusinessError, T]
}
