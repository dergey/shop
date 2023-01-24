package by.sergey.zhuravlev.shop.domain

import javax.persistence.*

@Entity
@Table(name = "attribute_values")
data class AttributeValue(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @ManyToOne
  @JoinColumn(name = "attribute_id", nullable = false)
  var attribute: Attribute? = null,

  @Column(name = "value", length = 100, nullable = false)
  var value: String? = null

)
