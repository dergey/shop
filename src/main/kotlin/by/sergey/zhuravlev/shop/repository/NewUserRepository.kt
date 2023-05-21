package by.sergey.zhuravlev.shop.repository

import by.sergey.zhuravlev.shop.domain.NewUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NewUserRepository : JpaRepository<NewUser, Long> {

  fun findByConfirmationLinkCode(linkCode: String): NewUser?

  fun findByContinuationCode(continuationCode: UUID?): NewUser?

  fun findByEmail(email: String): NewUser?

  fun findByPhone(phone: String): NewUser?

}