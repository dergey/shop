package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Catalog
import org.springframework.data.jpa.repository.JpaRepository

interface CatalogRepository : JpaRepository<Catalog, Long> {
}