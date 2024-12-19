package exporter

import models.BusinessError

trait Exporter[-T]
{
  def output(item: T): Either[BusinessError, Unit]
}


