package by.sergey.zhuravlev.shop.model

enum class ProductSortField(val columnName: String) {
  ID("id"),
  PRICE("price"),
  CREATE_AT("createAt"),
  //SCORE,
  //REVIEW_COUNT,
}