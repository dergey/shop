package by.sergey.zhuravlev.shop.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "cart_items")
data class CartItem(

  @EmbeddedId
  var id: CardItemId,

  @Column(name = "count", nullable = false)
  var count: Int

) {

  @Embeddable
  data class CardItemId(

    @ManyToOne
    @JoinColumn(name = "cart_id")
    var cart: Cart,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product

  ) : Serializable

}