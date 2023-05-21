package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.domain.Image
import by.sergey.zhuravlev.shop.domain.User
import by.sergey.zhuravlev.shop.exception.ShopServiceException
import by.sergey.zhuravlev.shop.exception.ShopServiceFieldException
import by.sergey.zhuravlev.shop.model.ErrorCode
import by.sergey.zhuravlev.shop.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserService(
  private val userRepository: UserRepository,
  //private val passwordEncoder: PasswordEncoder
) {

  @Transactional(readOnly = true)
  fun getCurrentUser(id: Long): User {
    //val email: String = SecurityUtils.getCurrentUserLogin().orElseThrow { RuntimeException("Unauthorized") }
    //return getUser(email)
    TODO("will be implemented")
  }

  @Transactional(readOnly = true)
  fun getUser(id: Long): User {
    return userRepository.findById(id)
      ?: throw ShopServiceException(ErrorCode.NOT_FOUND)
  }

  @Transactional(readOnly = true)
  fun getUser(email: String): User {
    return userRepository.findByEmail(email)
      ?: throw ShopServiceException(ErrorCode.NOT_FOUND)
  }

  @Transactional
  fun createUserWithEmail(
    email: String,
    rawPassword: String,
    firstName: String,
    middleName: String,
    secondName: String
  ): User {
    if (userRepository.findByEmail(email) != null) {
      throw ShopServiceFieldException("email", ErrorCode.ALREADY_EXIST)
    }
    val user = User(
      null,
      email,
      null,
      "todo",//passwordEncoder.encode(rawPassword),
      firstName,
      middleName,
      secondName,
      null,
      null,
      LocalDateTime.now(),
      LocalDateTime.now()
    )
    return userRepository.save(user)
  }

  @Transactional
  fun createUserWithPhone(
    phone: String,
    rawPassword: String,
    firstName: String,
    middleName: String,
    secondName: String
  ): User {
    if (userRepository.findByPhone(phone) != null) {
      throw ShopServiceFieldException("phone", ErrorCode.ALREADY_EXIST)
    }
    val user = User(
      null,
      null,
      phone,
      "todo",//passwordEncoder.encode(rawPassword),
      firstName,
      middleName,
      secondName,
      null,
      null,
      LocalDateTime.now(),
      LocalDateTime.now()
    )
    return userRepository.save(user)
  }

  @Transactional
  fun updateUserPassword(userId: Long, rawPassword: String): User {
    return userRepository.findById(userId)?.apply {
      this.password = "todo" //passwordEncoder.encode(rawPassword)
    } ?: throw ShopServiceException(ErrorCode.NOT_FOUND)
  }

  fun updateUserAvatar(userId: Long, newAvatar: Image): User {
    return userRepository.findById(userId)?.apply {
      this.avatar = newAvatar
    } ?: throw ShopServiceException(ErrorCode.NOT_FOUND)
  }
}