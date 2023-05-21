package by.sergey.zhuravlev.shop.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.web.cors.CorsConfiguration

@ConfigurationProperties("shop.cors")
class CorsProperties : CorsConfiguration()