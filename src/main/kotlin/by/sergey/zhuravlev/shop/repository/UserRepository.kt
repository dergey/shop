package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}