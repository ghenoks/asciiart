package exporter

import models.BusinessError

/*
 * Exports item somewhere
 * @param item - the item to export
 * Returns BusinessError if export fails
 */
trait Exporter[-T]
{
  def output(item: T): Either[BusinessError, Unit]
}


