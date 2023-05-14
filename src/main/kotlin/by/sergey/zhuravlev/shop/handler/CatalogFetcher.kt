package by.sergey.zhuravlev.shop.handler

import by.sergey.zhuravlev.shop.model.AttributeModel
import by.sergey.zhuravlev.shop.model.CatalogModel
import by.sergey.zhuravlev.shop.service.CatalogService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class CatalogFetcher(
  private val catalogService: CatalogService,
) {

  @QueryMapping
  fun getRootCatalog(): CatalogModel {
    return catalogService.getRootCatalog()
  }

  @QueryMapping
  fun getCatalog(@Argument catalogId: Long): CatalogModel {
    return catalogService.getCatalog(catalogId)
  }

  @SchemaMapping(typeName = "Catalog", field = "attributes")
  fun getCatalogAttributes(catalog: CatalogModel): List<AttributeModel> {
    return catalogService.getCatalogAttributes(catalog.id)
  }

  @SchemaMapping(typeName = "Catalog", field = "children")
  fun getCatalogChildren(catalog: CatalogModel): List<CatalogModel> {
    return catalogService.getCatalogChildren(catalog.id)
  }

}