package by.sergey.zhuravlev.shop.domain

import javax.persistence.*

@Entity
@Table(name = "attribute_values")
data class AttributeValue(

  //todo убрать излишний id!
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,

  @ManyToOne
  @JoinColumn(name = "attribute_id", nullable = false)
  var attribute: Attribute,

  @Column(name = "value", length = 100, nullable = false)
  var value: String

)
