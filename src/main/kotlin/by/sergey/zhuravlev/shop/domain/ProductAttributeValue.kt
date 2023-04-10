package by.sergey.zhuravlev.shop.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "product_attribute_values")
data class ProductAttributeValue(

  @EmbeddedId
  var id: ProductAttributeValueId

) {

  @Embeddable
  data class ProductAttributeValueId(

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,

    @ManyToOne
    @JoinColumn(name = "attribute_value_id")
    var attributeValue: AttributeValue

  ) : Serializable

}