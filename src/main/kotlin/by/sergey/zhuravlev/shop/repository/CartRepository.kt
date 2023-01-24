package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
}