package exporter

trait Exporter[-T]
{
  def output(item: T): Unit
}


