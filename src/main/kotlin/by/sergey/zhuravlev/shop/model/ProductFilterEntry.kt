package by.sergey.zhuravlev.shop.model

data class ProductFilterEntry(
  val type: ProductFilterType,
  val values: List<Any>,
)
