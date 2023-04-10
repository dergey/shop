package by.sergey.zhuravlev.shop.model

import org.springframework.data.domain.Sort

data class ProductSortEntry(
  val field: ProductSortField,
  val order: Sort.Direction
)