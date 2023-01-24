package by.sergey.zhuravlev.shop.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @ManyToOne
  @JoinColumn(name = "catalog_id")
  var catalog: Catalog? = null,

  @Column(name = "title", length = 140, nullable = false)
  var title: String? = null,

  @Lob
  @Column(name = "description", length = 512, nullable = false)
  var description: String? = null,

  @Column(name = "price", precision = 16, scale = 2, nullable = false)
  var price: BigDecimal? = null,

  @Column(name = "price_currency", length = 3, nullable = false)
  var priceCurrency: String? = null,

  @Enumerated(EnumType.STRING)
  @Column(name = "availability", length = 16, nullable = false)
  var availability: Availability? = null,

  @Column(name = "available_at", nullable = false)
  var availableAt: LocalDate? = null,

  @Column(name = "create_at", nullable = false)
  var createAt: LocalDateTime? = null,

  @Column(name = "update_at", nullable = false)
  var updateAt: LocalDateTime? = null,

  @ManyToMany
  @JoinTable(
    name = "product_images",
    joinColumns = [JoinColumn(name = "product_id")],
    inverseJoinColumns = [JoinColumn(name = "image_id")]
  )
  var images: List<Image>? = null,

  @ManyToMany
  @JoinTable(
    name = "product_attribute_values",
    joinColumns = [JoinColumn(name = "product_id")],
    inverseJoinColumns = [JoinColumn(name = "attribute_value_id")]
  )
  var attributeValues: List<AttributeValue>

)
