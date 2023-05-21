package by.sergey.zhuravlev.shop.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("social.mail")
data class MailProperties(
  val sender: String,
)