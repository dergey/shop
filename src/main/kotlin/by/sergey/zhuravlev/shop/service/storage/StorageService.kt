package by.sergey.zhuravlev.shop.service.storage

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream

interface StorageService {

  fun store(file: MultipartFile): String

  fun store(bytes: ByteArray): String

  fun load(id: String): ByteArray

  fun loadAsInputStream(id: String): InputStream

  fun loadAsResource(id: String): Resource

  fun contain(id: String): Boolean

  fun clear(id: String)

  fun clearAll()

}
