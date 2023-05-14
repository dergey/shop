package by.sergey.zhuravlev.shop.domain

import com.querydsl.core.annotations.QueryEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@QueryEntity
@Table(name = "product_attribute_values")
data class ProductAttributeValue(

  @EmbeddedId
  var id: ProductAttributeValueId

) {

  @Embeddable
  @QueryEntity
  data class ProductAttributeValueId(

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,

    @ManyToOne
    @JoinColumns(value = [
      JoinColumn(name="catalog_attribute_code", referencedColumnName = "catalog_attribute_code"),
      JoinColumn(name="catalog_attribute_catalog_id", referencedColumnName = "catalog_attribute_catalog_id"),
      JoinColumn(name="catalog_attribute_value", referencedColumnName = "value"),
    ])
    var catalogAttributeValue: CatalogAttributeValue

  ) : Serializable

}