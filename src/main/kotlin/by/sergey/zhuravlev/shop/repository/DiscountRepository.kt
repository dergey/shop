package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Catalog
import by.sergey.zhuravlev.shop.domain.Discount
import org.springframework.data.jpa.repository.JpaRepository

interface DiscountRepository : JpaRepository<Discount, Long> {
}