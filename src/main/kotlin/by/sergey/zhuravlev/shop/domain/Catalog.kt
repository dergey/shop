package by.sergey.zhuravlev.shop.domain

import com.querydsl.core.annotations.QueryEntity
import javax.persistence.*

@Entity
@QueryEntity
@Table(name = "catalogs")
data class Catalog(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @Column(name = "title", length = 100, nullable = false)
  var title: String,

  @ManyToOne
  @JoinColumn(name = "parent_id")
  var parent: Catalog? = null,

  @OneToMany(mappedBy = "id.catalog")
  var attributes: List<CatalogAttribute>

)
