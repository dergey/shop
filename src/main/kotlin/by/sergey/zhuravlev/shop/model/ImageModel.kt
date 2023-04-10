package by.sergey.zhuravlev.shop.model

import org.springframework.util.unit.DataSize
import java.time.LocalDateTime

data class ImageModel(
  val id: Long,
  val mimeType: String,
  val height: Int,
  val width: Int,
  val dataSize: DataSize,
  val createAt: LocalDateTime
)