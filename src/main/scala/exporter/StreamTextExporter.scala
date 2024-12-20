package exporter

import models.BusinessError

import java.io.OutputStream

/*
 * Exports text into stream
 * @param outputStream - stream to which text will be output
 */
class StreamTextExporter (outputStream: OutputStream) extends TextExporter {
  private var closed = false

  private def exportToStream(text: String): Either[BusinessError, Unit] = {

    if (closed)
      throw new Exception("The stream is already closed")

    outputStream.write(text.getBytes("UTF-8"))
    outputStream.flush()
    Right(())
  }

  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def output(item: String): Either[BusinessError, Unit] = exportToStream(item)

}
