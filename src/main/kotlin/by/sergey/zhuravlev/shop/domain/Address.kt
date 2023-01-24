package by.sergey.zhuravlev.shop.domain
  
import javax.persistence.*

@Entity
@Table(name = "addresses")
data class Address(

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, updatable = false)
  var id: Long? = null,

  @Column(name = "first_line", length = 255)
  var firstLine: String? = null,

  @Column(name = "second_line", length = 255)
  var secondLine: String? = null,

  @Column(name = "city", length = 180)
  var city: String? = null,

  @Column(name = "country", length = 2)
  var country: String? = null,

  @Column(name = "zip_code", length = 18)
  var zipCode: String? = null

)