package by.sergey.zhuravlev.shop.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*


//todo сделать более гибкую систему скидок, возжность сетать их на категорию, возможность установить процентную скидку.
@Entity
@Table(name = "discounts")
data class Discount(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  var product: Product? = null,

  @Column(name = "price", precision = 16, scale = 2, nullable = false)
  var price: BigDecimal,

  @Column(name = "price_currency", length = 3, nullable = false)
  var priceCurrency: String,

  @Column(name = "start_at", nullable = false)
  var startAt: LocalDateTime,

  @Column(name = "end_at", nullable = false)
  var endAt: LocalDateTime,

)