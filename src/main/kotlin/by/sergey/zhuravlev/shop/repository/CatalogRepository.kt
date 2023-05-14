package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Catalog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CatalogRepository : JpaRepository<Catalog, Long> {

  @Query("select p.catalog from Product p where p.id = :productId")
  fun findProductCatalogByProductId(@Param("productId") productId: Long): Catalog?

  @Query("select c from Catalog c where c.parent is null")
  fun findCatalogWithNullParent(): Catalog?

  fun findCatalogById(catalogId: Long): Catalog?

  fun findAllByParentId(catalogId: Long): List<Catalog>

}