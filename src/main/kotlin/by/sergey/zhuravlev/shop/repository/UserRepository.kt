package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.User
import org.springframework.data.repository.Repository

interface UserRepository : Repository<User, Long> {

  fun save(user: User): User

  fun findById(id: Long): User?

  fun findByPhone(phone: String): User?

  fun findByEmail(email: String): User?

}