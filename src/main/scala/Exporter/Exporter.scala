package Exporter

trait Exporter[T]
{
  def output(item: T): Unit
}


