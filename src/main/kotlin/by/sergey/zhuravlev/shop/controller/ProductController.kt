package by.sergey.zhuravlev.shop.controller

import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class ProductController {

  @SchemaMapping(typeName = "Query", value = "getProductByFilter")
  fun getProductByFilter(filter: ProductFilter) {

  }

}