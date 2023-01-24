package by.sergey.zhuravlev.shop.converter

import java.util.*
import javax.persistence.AttributeConverter

class UUIDConverter : AttributeConverter<UUID?, String?> {
  override fun convertToDatabaseColumn(uuid: UUID?): String? {
    return uuid?.toString()
  }

  override fun convertToEntityAttribute(s: String?): UUID? {
    return if (s != null) {
      UUID.fromString(s)
    } else {
      null
    }
  }
}