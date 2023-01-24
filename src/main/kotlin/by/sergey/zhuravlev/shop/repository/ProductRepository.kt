package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
}