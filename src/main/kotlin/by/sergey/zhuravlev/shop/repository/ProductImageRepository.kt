package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.ProductImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImage, ProductImage.ProductImageId> {

  fun findAllByIdProductId(productId: Long): List<ProductImage>

}