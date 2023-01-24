package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Address
import by.sergey.zhuravlev.shop.domain.Attribute
import org.springframework.data.jpa.repository.JpaRepository

interface AttributeRepository : JpaRepository<Attribute, Long> {
}