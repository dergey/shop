package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.Discount
import by.sergey.zhuravlev.shop.domain.Product
import by.sergey.zhuravlev.shop.model.ProductModel
import java.math.BigDecimal

object ProductModelFactory {

  fun buildProductModel(product: Product, discount: Discount?): ProductModel {
    val originalPrice = buildPriceString(product.price, product.priceCurrency)
    return ProductModel(
      id = product.id!!,
      title = product.title,
      description = product.description,
      price = discount?.let { buildPriceString(it.price, it.priceCurrency) } ?: originalPrice,
      originalPrice = originalPrice,
      availability = product.availability,
      availableAt = product.availableAt,
      createAt = product.createAt,
      updateAt = product.updateAt,
    )
  }

  private fun buildPriceString(amount: BigDecimal, currency: String): String {
    return "${amount.toPlainString()} $currency"
  }

}