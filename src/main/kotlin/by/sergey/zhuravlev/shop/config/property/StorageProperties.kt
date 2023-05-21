package by.sergey.zhuravlev.shop.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("social.storage")
data class StorageProperties(
  var location: String
)
