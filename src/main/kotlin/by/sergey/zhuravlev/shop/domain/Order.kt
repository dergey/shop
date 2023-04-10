package by.sergey.zhuravlev.shop.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class Order(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @Column(name = "status", length = 16, nullable = false)
  var status: OrderStatus,

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = true)
  var user: User? = null,

  @Column(name = "recipient_phone", length = 15, nullable = false)
  var recipientPhone: String,

  @Column(name = "recipient_name", length = 200, nullable = false)
  var recipientName: String,

  @Column(name = "amount", precision = 16, scale = 2, nullable = false)
  var amount: BigDecimal,

  @Column(name = "amount_currency", length = 3, nullable = false)
  var amountCurrency: BigDecimal,

  @Column(name = "delivery", length = 16, nullable = false)
  var delivery: DeliveryType,

  @Column(name = "create_at", nullable = false)
  var createAt: LocalDateTime,

  @Column(name = "update_at", nullable = false)
  var updateAt: LocalDateTime,

  @Column(name = "delivery_at")
  var deliveryAt: LocalDateTime? = null,

  @Column(name = "complete_at")
  var completeAt: LocalDateTime? = null,

  @OneToMany(mappedBy = "id.order")
  var items: List<OrderItem>
)