package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.domain.NewUser
import by.sergey.zhuravlev.shop.domain.RegistrationStatus
import by.sergey.zhuravlev.shop.domain.User
import by.sergey.zhuravlev.shop.exception.ShopServiceException
import by.sergey.zhuravlev.shop.exception.ShopServiceFieldException
import by.sergey.zhuravlev.shop.model.ErrorCode
import by.sergey.zhuravlev.shop.repository.UserRepository
import org.apache.logging.log4j.util.Strings
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class RegistrationService(
  private val newUserService: NewUserService,
  private val userService: UserService,
  private val emailService: EmailService,
  private val userRepository: UserRepository
) {

  @Transactional
  fun startRegistration(phoneOrEmail: String): NewUser {
    val matcher: Matcher = EMAIL_OR_PHONE_PATTERN.matcher(phoneOrEmail)
    if (!matcher.matches()) {
      throw ShopServiceFieldException("phoneOrEmail", ErrorCode.INVALID_EMAIL_OR_PHONE_FORMAT)
    }
    val phone = matcher.group(1)
    val email = matcher.group(2)
    val newUser: NewUser
    if (Strings.isBlank(phone) && Strings.isNotBlank(email)) {
      if (userRepository.findByEmail(email) != null) {
        throw ShopServiceFieldException("phoneOrEmail", ErrorCode.ALREADY_EXIST)
      }
      newUser = newUserService.createNewUserWithEmail(email)
      emailService.sendTemplate(
        email,
        "registration.confirmation",
        "registration.confirmation.ftlh",
        newUser.confirmation
      )
    } else {
      newUser = newUserService.createNewUserWithPhone(phone)
    }
    return newUser
  }

  @Transactional(noRollbackFor = [ShopServiceException::class])
  fun confirmByManualCode(continuationCode: UUID, manualCode: String): NewUser {
    val newUser: NewUser = newUserService.getNewUserByContinuationCode(continuationCode)
    if (newUser.registrationStatus != RegistrationStatus.EMAIL_CONFIRMATION &&
      newUser.registrationStatus != RegistrationStatus.PHONE_CONFIRMATION
    ) {
      throw ShopServiceException(ErrorCode.INVALID_NEW_USER_STATE)
    }
    newUserService.confirmByManualCode(newUser, manualCode)
    return newUser
  }

  @Transactional(noRollbackFor = [ShopServiceException::class])
  fun confirmByLinkCode(linkCode: String): NewUser {
    val newUser: NewUser = newUserService.getNewUserByLinkCode(linkCode)
    if (newUser.registrationStatus != RegistrationStatus.EMAIL_CONFIRMATION &&
      newUser.registrationStatus != RegistrationStatus.PHONE_CONFIRMATION
    ) {
      throw ShopServiceException(ErrorCode.INVALID_NEW_USER_STATE)
    }
    newUserService.confirmByLinkCode(newUser, linkCode)
    return newUser
  }

  @Transactional
  fun resendConfirmation(continuationCode: UUID): NewUser {
    val newUser: NewUser = newUserService.getNewUserByContinuationCode(continuationCode)
    if (newUser.registrationStatus != RegistrationStatus.EMAIL_CONFIRMATION &&
      newUser.registrationStatus != RegistrationStatus.PHONE_CONFIRMATION
    ) {
      throw ShopServiceException(ErrorCode.INVALID_NEW_USER_STATE)
    }
    newUserService.renewConfirmation(newUser)
    newUser.email?.also { email ->
      emailService.sendTemplate(
        email,
        "registration.confirmation",
        "registration.confirmation.ftlh",
        newUser.confirmation
      )
    }
    return newUser
  }

  @Transactional(propagation = Propagation.NOT_SUPPORTED)
  fun completeRegistration(
    continuationCode: UUID,
    rawPassword: String,
    firstName: String,
    middleName: String,
    secondName: String
  ): User {
    val newUser: NewUser = newUserService.getNewUserByContinuationCode(continuationCode)
    if (newUser.registrationStatus != RegistrationStatus.PERSONAL_DATA_AWAIT) {
      throw ShopServiceException(ErrorCode.INVALID_NEW_USER_STATE)
    }
    val user = when {
      newUser.email != null ->
        userService.createUserWithEmail(newUser.email!!, rawPassword, firstName, middleName, secondName)
      newUser.phone != null ->
        userService.createUserWithPhone(newUser.phone!!, rawPassword, firstName, middleName, secondName)
      else -> throw IllegalStateException("Email or phone not provided")
    }
    return user
  }

  companion object {
    private val EMAIL_OR_PHONE_PATTERN = Pattern.compile("(\\d{7,15})|(\\w+@\\w+\\.\\w{2,3})")
  }
}