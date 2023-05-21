package by.sergey.zhuravlev.shop.config

import by.sergey.zhuravlev.shop.config.property.CorsProperties
import by.sergey.zhuravlev.shop.config.property.FrontendMailProperties
import by.sergey.zhuravlev.shop.config.property.MailProperties
import by.sergey.zhuravlev.shop.config.property.StorageProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableAsync
@EnableConfigurationProperties(
  CorsProperties::class,
  StorageProperties::class,
  MailProperties::class,
  FrontendMailProperties::class
)
class ShopAppConfiguration