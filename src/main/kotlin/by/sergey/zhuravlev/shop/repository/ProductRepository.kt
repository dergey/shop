package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface ProductRepository : JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

  fun findProductById(productId: Long): Product?

}