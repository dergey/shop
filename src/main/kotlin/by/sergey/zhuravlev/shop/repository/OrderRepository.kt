package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Image
import by.sergey.zhuravlev.shop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}