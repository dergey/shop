package by.sergey.zhuravlev.shop.domain

import javax.persistence.*

@Entity
@Table(name = "carts")
data class Cart (

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long,

  @Column(name = "secret", length = 60)
  var secret: String? = null,

  @ManyToOne
  @JoinColumn(name = "user_id")
  var user: User? = null,

  @OneToMany(mappedBy = "id.cart")
  var products: List<CartItem>,

)