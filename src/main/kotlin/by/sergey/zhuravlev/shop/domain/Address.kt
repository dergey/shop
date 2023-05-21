package by.sergey.zhuravlev.shop.domain
  
import javax.persistence.*

@Entity
@Table(name = "addresses")
data class Address(

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  var id: Long? = null,

  @Column(name = "country", length = 2)
  var country: String? = null,

  @Column(name = "region", length = 180)
  var region: String? = null,

  @Column(name = "city", length = 180)
  var city: String? = null,

  @Column(name = "street", length = 255)
  var street: String? = null,

  @Column(name = "home")
  var home: Int? = null,

  @Column(name = "flat")
  var flat: Int? = null,

  @Column(name = "floor")
  var floor: Int? = null,

  @Column(name = "entrance")
  var entrance: Int? = null,

  @Column(name = "zip_code", length = 18)
  var zipCode: String? = null

)