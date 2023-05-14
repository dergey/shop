package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.ProductAttributeValue
import org.springframework.data.jpa.repository.JpaRepository

interface ProductAttributeValueRepository :
  JpaRepository<ProductAttributeValue, ProductAttributeValue.ProductAttributeValueId> {

  fun findAllByIdProductId(productId: Long): List<ProductAttributeValue>

}