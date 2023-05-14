package by.sergey.zhuravlev.shop.domain

import com.querydsl.core.annotations.QueryEntity
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@QueryEntity
@Table(name = "products")
data class Product(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "catalog_id")
  var catalog: Catalog? = null,

  @Column(name = "title", length = 140, nullable = false)
  var title: String,

  @Lob
  @Column(name = "description", length = 512, nullable = false)
  var description: String,

  @Column(name = "price", precision = 16, scale = 2, nullable = false)
  var price: BigDecimal,

  @Column(name = "price_currency", length = 3, nullable = false)
  var priceCurrency: String,

  @Enumerated(EnumType.STRING)
  @Column(name = "availability", length = 16, nullable = false)
  var availability: Availability,

  @Column(name = "available_at")
  var availableAt: LocalDate? = null,

  @Column(name = "create_at", nullable = false)
  var createAt: LocalDateTime,

  @Column(name = "update_at", nullable = false)
  var updateAt: LocalDateTime,

  @OneToMany(mappedBy = "id.product")
  var images: List<ProductImage>,

  @OneToMany(mappedBy = "id.product")
  var attributeValues: List<ProductAttributeValue>

)
