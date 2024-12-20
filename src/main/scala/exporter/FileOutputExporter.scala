package exporter

import java.io.{File, FileOutputStream}

/*
 * Exports text into File
 */
class FileOutputExporter (file: File)
  extends StreamTextExporter(new FileOutputStream(file))
{

}

