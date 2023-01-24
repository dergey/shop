package by.sergey.zhuravlev.shop.converter

import org.springframework.util.unit.DataSize
import javax.persistence.AttributeConverter

class DataSizeConverter : AttributeConverter<DataSize?, String?> {
  override fun convertToDatabaseColumn(dataSize: DataSize?): String? {
    return dataSize?.toString()
  }

  override fun convertToEntityAttribute(s: String?): DataSize? {
    return if (s != null) {
      DataSize.parse(s)
    } else {
      null
    }
  }
}