package by.sergey.zhuravlev.shop.domain

import by.sergey.zhuravlev.shop.converter.DataSizeConverter
import org.springframework.util.unit.DataSize
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class Image(

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  var id: Long? = null,

  @Column(name = "mime_type", length = 128, nullable = false, updatable = false)
  var mimeType: String,

  @Column(name = "height", nullable = false, updatable = false)
  var height: Int,

  @Column(name = "width", nullable = false, updatable = false)
  var width: Int,

  @Convert(converter = DataSizeConverter::class)
  @Column(name = "data_size", nullable = false, updatable = false)
  var dataSize: DataSize,

  @Column(name = "storage_id", length = 80, nullable = false, updatable = false)
  var storageId: String,

  @Column(name = "create_at", nullable = false, updatable = false)
  var createAt: LocalDateTime

)