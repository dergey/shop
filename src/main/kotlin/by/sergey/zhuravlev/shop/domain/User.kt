package by.sergey.zhuravlev.shop.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @Column(name = "email", length = 191, unique = true)
  var email: String? = null,

  @Column(name = "phone", length = 15, unique = true)
  var phone: String? = null,

  @Column(name = "password", length = 60, nullable = false)
  var password: String,

  @Column(name = "first_name", nullable = false)
  var firstName: String,

  @Column(name = "middle_name")
  var middleName: String? = null,

  @Column(name = "second_name", nullable = false)
  var secondName: String,

  @ManyToOne
  @JoinColumn(name = "avatar")
  var avatar: Image? = null,

  @ManyToMany
  @JoinTable(
    name = "user_addresses",
    joinColumns = [JoinColumn(name = "user_id")],
    inverseJoinColumns = [JoinColumn(name = "address_id")]
  )
  var addresses: List<Address>? = null,

  @Column(name = "create_at", nullable = false, updatable = false)
  var createAt: LocalDateTime,

  @Column(name = "update_at", nullable = false)
  var updateAt: LocalDateTime

)