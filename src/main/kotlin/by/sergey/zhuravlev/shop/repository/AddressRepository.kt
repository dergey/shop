package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Address, Long> {
}