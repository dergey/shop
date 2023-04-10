package by.sergey.zhuravlev.shop.model

data class CatalogModel(
  val id: Long,
  val name: String,
  val parent: CatalogModel?
)