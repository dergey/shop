package by.sergey.zhuravlev.shop.model

data class CartModel(
  val secret: String,
  val amount: String,
  val discount: String,
  val items: List<CartItemModel>,
)
