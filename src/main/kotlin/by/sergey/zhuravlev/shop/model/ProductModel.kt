package by.sergey.zhuravlev.shop.model

import by.sergey.zhuravlev.shop.domain.Availability
import java.time.LocalDate
import java.time.LocalDateTime

data class ProductModel(
  val id: Long,
  val title: String,
  val description: String,
  val price: String,
  val originalPrice: String?,
  val availability: Availability,
  val availableAt: LocalDate?,
  val createAt: LocalDateTime,
  val updateAt: LocalDateTime,
)