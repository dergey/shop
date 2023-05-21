package by.sergey.zhuravlev.shop.service.storage

import by.sergey.zhuravlev.shop.config.property.StorageProperties
import by.sergey.zhuravlev.shop.service.storage.exception.StorageException
import by.sergey.zhuravlev.shop.service.storage.exception.StorageFileNotFoundException
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*
import javax.annotation.PostConstruct

@Service
class FileSystemStorageService(properties: StorageProperties) : StorageService {

  private val rootLocation: Path = Paths.get(properties.location)

  @PostConstruct
  fun init() {
    try {
      Files.createDirectories(rootLocation)
    } catch (e: IOException) {
      throw StorageException("Could not initialize storage", e)
    }
  }

  override fun store(file: MultipartFile): String {
    return try {
      if (file.isEmpty()) {
        throw StorageException("Failed to store empty file.")
      }
      val destinationFilename = generateNewFilename()
      val destinationFile = rootLocation.resolve(Paths.get(destinationFilename)).normalize().toAbsolutePath()
      if (destinationFile.parent != rootLocation.toAbsolutePath()) {
        throw StorageException("Cannot store file outside current directory.")
      }
      file.inputStream
        .use { inputStream -> Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING) }
      destinationFilename
    } catch (e: IOException) {
      throw StorageException("Failed to store file.", e)
    }
  }

  override fun store(bytes: ByteArray): String {
    return try {
      if (bytes.isEmpty()) {
        throw StorageException("Failed to store empty file.")
      }
      val destinationFilename = generateNewFilename()
      val destinationFile = rootLocation.resolve(Paths.get(destinationFilename)).normalize().toAbsolutePath()
      if (destinationFile.parent != rootLocation.toAbsolutePath()) {
        throw StorageException("Cannot store file outside current directory.")
      }
      ByteArrayInputStream(bytes).use { inputStream ->
        Files.copy(
          inputStream,
          destinationFile,
          StandardCopyOption.REPLACE_EXISTING
        )
      }
      destinationFilename
    } catch (e: IOException) {
      throw StorageException("Failed to store file.", e)
    }
  }

  override fun load(id: String): ByteArray {
    val resource = loadAsResource(id)
    val bufferLength = 4 * 0x400 // 4KB
    val buf = ByteArray(bufferLength)
    var readLength: Int
    val inputStream = resource.inputStream
    ByteArrayOutputStream().use { outputStream ->
      while (inputStream.read(buf, 0, bufferLength).also { readLength = it } != -1) {
        outputStream.write(buf, 0, readLength)
      }
      inputStream.close()
      return outputStream.toByteArray()
    }
  }

  override fun loadAsInputStream(id: String): InputStream {
    val resource = loadAsResource(id)
    return resource.inputStream
  }

  override fun loadAsResource(id: String): Resource {
    return try {
      val file = rootLocation.resolve(id)
      val resource: Resource = UrlResource(file.toUri())
      if (resource.exists() || resource.isReadable) {
        resource
      } else {
        throw StorageFileNotFoundException("Could not read file: $id")
      }
    } catch (e: MalformedURLException) {
      throw StorageFileNotFoundException("Could not read file: $id", e)
    }
  }

  override fun contain(id: String): Boolean {
    val path = rootLocation.resolve(id)
    return Files.exists(path) && Files.isReadable(path)
  }

  override fun clear(id: String) {
    val path = rootLocation.resolve(id)
    if (!Files.exists(path)) {
      throw StorageFileNotFoundException("Could not read file: $id")
    }
    try {
      Files.delete(path)
    } catch (e: IOException) {
      throw StorageFileNotFoundException("Could not read file: $id", e)
    }
  }

  override fun clearAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile())
  }

  private fun generateNewFilename(): String {
    return UUID.randomUUID().toString()
  }
}