package by.sergey.zhuravlev.shop.converter

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.data.domain.Page
import java.io.IOException

class PageSerializer @JvmOverloads constructor(t: Class<Page<*>?>? = null) : StdSerializer<Page<*>>(t) {
  @Throws(IOException::class, JsonProcessingException::class)
  override fun serialize(
    value: Page<*>, jgen: JsonGenerator, provider: SerializerProvider
  ) {
    jgen.writeStartObject()
    jgen.writeNumberField("totalPages", value.totalPages)
    jgen.writeNumberField("totalElements", value.totalElements)
    jgen.writeNumberField("size", value.size)
    jgen.writeNumberField("number", value.number)
    jgen.writeBooleanField("hasNext", value.hasNext())
    jgen.writeObjectField("content", value.content)
    jgen.writeEndObject()
  }
}