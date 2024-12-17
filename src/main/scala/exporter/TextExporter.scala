package exporter

trait TextExporter extends Exporter[String]
{
  def close(): Unit = {}
}

