package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.CatalogAttribute
import org.springframework.data.jpa.repository.JpaRepository

interface CatalogAttributeRepository : JpaRepository<CatalogAttribute, CatalogAttribute.CatalogAttributeId> {

  fun findAllByIdCatalogId(catalogId: Long): List<CatalogAttribute>

}