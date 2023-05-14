package by.sergey.zhuravlev.shop.domain

import com.querydsl.core.annotations.QueryEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@QueryEntity
@Table(name = "catalog_attribute_values")
data class CatalogAttributeValue(

  @EmbeddedId
  var id: CatalogAttributeValueId

) {

  @Embeddable
  @QueryEntity
  data class CatalogAttributeValueId(

    @ManyToOne
    @JoinColumns(value = [
      JoinColumn(name="catalog_attribute_code", referencedColumnName = "code"),
      JoinColumn(name="catalog_attribute_catalog_id", referencedColumnName = "catalog_id")
    ])
    var attribute: CatalogAttribute? = null,

    @Column(name = "value", length = 100, nullable = false)
    var value: String

  ) : Serializable

}
