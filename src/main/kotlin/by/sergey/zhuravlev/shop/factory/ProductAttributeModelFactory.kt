package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.ProductAttributeValue
import by.sergey.zhuravlev.shop.model.ProductAttributeModel

object ProductAttributeModelFactory {

  fun buildProductAttributeModel(attributeValue: ProductAttributeValue): ProductAttributeModel {
    return ProductAttributeModel(
      name = attributeValue.id.catalogAttributeValue.id.attribute?.title ?: throw IllegalArgumentException("attribute"),
      value = attributeValue.id.catalogAttributeValue.id.value
    )
  }

}