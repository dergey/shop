package by.sergey.zhuravlev.shop.model

data class CartItemModel(
  val count: Int,
  val amount: String,
  val discount: String,
  val product: ProductModel
)
