package by.sergey.zhuravlev.shop.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("shop.mail.frontend")
data class FrontendMailProperties(
  val host: String,
  val registrationLinkPath: String,
  val registrationLinkCodeParameter: String,
  val passwordResetLinkPath: String,
  val passwordResetLinkCodeParameter: String,
)