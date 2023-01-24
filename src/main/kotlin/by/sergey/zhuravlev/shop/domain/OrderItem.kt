package by.sergey.zhuravlev.shop.domain

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "cart_items")
data class OrderItem(

  @EmbeddedId
  var id: OrderItemId,

  @Column(name = "count", nullable = false)
  var count: Int,

  @Column(name = "amount", precision = 16, scale = 2, nullable = false)
  var amount: BigDecimal,

  @Column(name = "amount_currency", length = 3, nullable = false)
  var amountCurrency: BigDecimal,

) {

  @Embeddable
  data class OrderItemId(

    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product

  ) : Serializable

}