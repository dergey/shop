package by.sergey.zhuravlev.shop.domain

import by.sergey.zhuravlev.shop.converter.UUIDConverter
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "new_users", indexes = [
  Index(name = "IX_CONTINUATION_CODE", columnList = "continuation_code")
])
data class NewUser(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  @Convert(converter = UUIDConverter::class)
  @Column(name = "continuation_code", length = 36, nullable = false)
  var continuationCode: UUID,

  @Column(name = "email", length = 191, unique = true)
  var email: String? = null,

  @Column(name = "phone", length = 15, unique = true)
  var phone: String? = null,

  @Enumerated(EnumType.STRING)
  @Column(name = "registration_status", length = 20, nullable = false)
  var registrationStatus: RegistrationStatus,

  @Embedded
  val confirmation: Confirmation,

  @Column(name = "create_at", nullable = false, updatable = false)
  var createAt: LocalDateTime,

)