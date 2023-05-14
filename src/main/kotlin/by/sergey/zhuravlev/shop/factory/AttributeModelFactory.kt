package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.CatalogAttribute
import by.sergey.zhuravlev.shop.model.AttributeModel

object AttributeModelFactory {

  fun buildAttributeModel(catalogAttribute: CatalogAttribute): AttributeModel {
    return AttributeModel(
      name = catalogAttribute.title,
      values = catalogAttribute.values.map { it.id.value }
    )
  }

}