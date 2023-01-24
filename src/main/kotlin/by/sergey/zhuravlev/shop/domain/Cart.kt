package by.sergey.zhuravlev.shop.domain

import javax.persistence.*

@Entity
@Table(name = "carts")
data class Cart (

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,

  @Column(name = "secret", length = 60, nullable = false)
  var secret: String? = null,

  @ManyToOne
  @JoinColumn(name = "user_id")
  var user: User? = null,

  @ManyToMany
  @JoinTable(
    name = "cart_products",
    joinColumns = [JoinColumn(name = "cart_id")],
    inverseJoinColumns = [JoinColumn(name = "product_id")]
  )
  var products: List<Product>? = null,
)