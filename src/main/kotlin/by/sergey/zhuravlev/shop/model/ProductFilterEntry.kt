package by.sergey.zhuravlev.shop.model

import com.fasterxml.jackson.annotation.JsonRawValue

data class ProductFilterEntry(
  val type: ProductFilterType,
  @JsonRawValue
  val value: String?,
  val values: List<String>?
)
