package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.ProductAttributeValue
import by.sergey.zhuravlev.shop.model.ProductAttributeModel

object ProductAttributeModelFactory {

  fun buildProductAttributeModel(attributeValue: ProductAttributeValue): ProductAttributeModel {
    return ProductAttributeModel(
      name = attributeValue.id.attributeValue.attribute.title,
      value = attributeValue.id.attributeValue.value
    )
  }

}