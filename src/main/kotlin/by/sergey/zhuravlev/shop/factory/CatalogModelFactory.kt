package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.Catalog
import by.sergey.zhuravlev.shop.model.CatalogModel

object CatalogModelFactory {

  fun buildCatalogModel(catalog: Catalog): CatalogModel {
    if (catalog.id == null) {
      throw IllegalArgumentException("Catalog not created")
    }
    return CatalogModel(
      id = catalog.id!!,
      name = catalog.title,
      parent = catalog.parent?.let { buildCatalogModel(it) },
    )
  }

}