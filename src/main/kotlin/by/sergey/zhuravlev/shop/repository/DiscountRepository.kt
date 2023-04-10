package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Discount
import by.sergey.zhuravlev.shop.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface DiscountRepository : JpaRepository<Discount, Long> {

  fun findByProduct(product: Product): Discount

}