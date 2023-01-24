package by.sergey.zhuravlev.shop.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "discounts")
data class Discount(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @ManyToOne
  @JoinColumn(name = "product_id")
  var product: Product? = null,

  @Column(name = "price", precision = 16, scale = 2, nullable = false)
  var price: BigDecimal? = null,

  @Column(name = "price_currency", length = 3, nullable = false)
  var priceCurrency: String? = null,

  @Column(name = "start_at", nullable = false)
  var startAt: LocalDateTime,

  @Column(name = "end_at", nullable = false)
  var endAt: LocalDateTime,

)