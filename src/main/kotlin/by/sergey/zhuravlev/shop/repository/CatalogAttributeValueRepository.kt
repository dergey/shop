package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.CatalogAttributeValue
import org.springframework.data.jpa.repository.JpaRepository

interface CatalogAttributeValueRepository :
  JpaRepository<CatalogAttributeValue, CatalogAttributeValue.CatalogAttributeValueId> {
}