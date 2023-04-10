package by.sergey.zhuravlev.shop.handler

import by.sergey.zhuravlev.shop.model.CatalogModel
import by.sergey.zhuravlev.shop.model.ImageModel
import by.sergey.zhuravlev.shop.model.ProductAttributeModel
import by.sergey.zhuravlev.shop.model.ProductModel
import by.sergey.zhuravlev.shop.service.ProductService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Component

@Component
class ProductFetcher(
  private val productService: ProductService,
) {

  @QueryMapping
  fun getProduct(@Argument productId: Long): ProductModel {
    return productService.getProductById(productId)
  }

  @SchemaMapping(typeName = "Product", field = "catalog")
  fun getProductCatalog(product: ProductModel): CatalogModel {
    return productService.getProductCatalog(product.id)
  }

  @SchemaMapping(typeName = "Product", field = "images")
  fun getProductImages(product: ProductModel): List<ImageModel> {
    return productService.getProductImages(product.id)
  }

  @SchemaMapping(typeName = "Product", field = "attributes")
  fun getProductAttributes(product: ProductModel): List<ProductAttributeModel> {
    return productService.getProductAttributes(product.id)
  }
}