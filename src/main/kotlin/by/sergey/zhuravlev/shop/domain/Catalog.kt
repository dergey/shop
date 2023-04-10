package by.sergey.zhuravlev.shop.domain

import javax.persistence.*

@Entity
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

  @OneToMany(mappedBy = "catalog")
  var attributes: List<Attribute>

)
