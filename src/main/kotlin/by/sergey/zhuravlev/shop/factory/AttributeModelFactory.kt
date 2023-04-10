package by.sergey.zhuravlev.shop.factory

import by.sergey.zhuravlev.shop.domain.Attribute
import by.sergey.zhuravlev.shop.model.AttributeModel

object AttributeModelFactory {

  fun buildAttributeModel(attribute: Attribute): AttributeModel {
    return AttributeModel(
      name = attribute.title,
      values = attribute.values.map { it.value }
    )
  }

}