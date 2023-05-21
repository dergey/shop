package by.sergey.zhuravlev.shop.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class Confirmation(

  @Enumerated(EnumType.STRING)
  @Column(name = "type", length = 10)
  var type: ConfirmationType,

  @Column(name = "manual_code", length = 40)
  var manualCode: String,

  @Column(name = "manual_code_tries")
  var manualCodeTries: Int,

  @Column(name = "link_code", length = 40)
  var linkCode: String? = null,

  @Column(name = "valid_until")
  var validUntil: LocalDateTime
)