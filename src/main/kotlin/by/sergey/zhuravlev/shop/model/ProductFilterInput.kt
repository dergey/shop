package by.sergey.zhuravlev.shop.model

data class ProductFilterInput(
  val entries: List<ProductFilterEntry>,
  val sort: ProductSortEntry?,
  val limit: Long?,
  val offset: Long?
)