package by.sergey.zhuravlev.shop.uilts

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.activation.UnsupportedDataTypeException
import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.imageio.stream.ImageInputStream

fun InputStream.toImageReader(): ImageReader {
  val iis: ImageInputStream = ImageIO.createImageInputStream(this)
  val readers: Iterator<ImageReader> = ImageIO.getImageReaders(iis)
  if (readers.hasNext()) {
    val imageReader = readers.next()
    imageReader.setInput(iis, true)
    return imageReader
  }
  throw UnsupportedDataTypeException("Can't detect file reader")
}


fun BufferedImage.toByteArray(): ByteArray {
  val baos = ByteArrayOutputStream()
  ImageIO.write(this, "JPEG", baos)
  val data = baos.toByteArray()
  baos.close()
  return data
}