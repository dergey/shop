package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long> {
}