package by.sergey.zhuravlev.shop.domain

import com.querydsl.core.annotations.QueryEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@QueryEntity
@Table(name = "catalog_attributes")
data class CatalogAttribute(

  @EmbeddedId
  var id: CatalogAttributeId,

  @Column(name = "title", length = 100, nullable = false)
  var title: String,

  @OneToMany(mappedBy = "id.attribute")
  var values: List<CatalogAttributeValue>

) {

  @Embeddable
  @QueryEntity
  data class CatalogAttributeId(

    @Column(name = "code", length = 30, nullable = false)
    var code: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    var catalog: Catalog? = null,

  ) : Serializable

}
