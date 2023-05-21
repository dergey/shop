package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.domain.ConfirmationType
import by.sergey.zhuravlev.shop.domain.NewUser
import by.sergey.zhuravlev.shop.domain.RegistrationStatus
import by.sergey.zhuravlev.shop.exception.ShopServiceFieldException
import by.sergey.zhuravlev.shop.model.ErrorCode
import by.sergey.zhuravlev.shop.repository.NewUserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class NewUserService(
  private val confirmationService: ConfirmationService,
  private val newUserRepository: NewUserRepository,
) {

  fun getNewUserByLinkCode(linkCode: String): NewUser {
    return newUserRepository.findByConfirmationLinkCode(linkCode)
      ?: throw ShopServiceFieldException("linkCode", ErrorCode.NOT_VALID)
  }

  fun getNewUserByContinuationCode(continuationCode: UUID?): NewUser {
    return newUserRepository.findByContinuationCode(continuationCode)
      ?: throw ShopServiceFieldException("continuationCode", ErrorCode.NOT_FOUND)
  }

  fun createNewUserWithPhone(phone: String): NewUser {
    return newUserRepository.findByPhone(phone)
      ?.also { newUser -> renewConfirmation(newUser) }
      ?: newUserRepository.save(
        NewUser(
          null,
          UUID.randomUUID(),
          null,
          phone,
          RegistrationStatus.PHONE_CONFIRMATION,
          confirmationService.createPhoneConfirmation(),
          LocalDateTime.now()
        )
      )
  }

  fun createNewUserWithEmail(email: String): NewUser {
    return newUserRepository.findByEmail(email)
      ?.also { newUser -> renewConfirmation(newUser) }
      ?: newUserRepository.save(
        NewUser(
          null,
          UUID.randomUUID(),
          email,
          null,
          RegistrationStatus.EMAIL_CONFIRMATION,
          confirmationService.createEmailConfirmation(),
          LocalDateTime.now()
        )
      )
  }

  fun renewConfirmation(newUser: NewUser) {
    confirmationService.resetConfirmation(newUser.confirmation)
    newUser.continuationCode = UUID.randomUUID()
    if (newUser.confirmation.type == ConfirmationType.EMAIL) {
      newUser.registrationStatus = RegistrationStatus.EMAIL_CONFIRMATION
    } else {
      newUser.registrationStatus = RegistrationStatus.PHONE_CONFIRMATION
    }
  }

  fun confirmByManualCode(newUser: NewUser, manualCode: String?) {
    confirmationService.validateManualCode(newUser.confirmation, manualCode)
    newUser.registrationStatus = RegistrationStatus.PERSONAL_DATA_AWAIT
    newUser.continuationCode = UUID.randomUUID()
  }

  fun confirmByLinkCode(newUser: NewUser, linkCode: String?) {
    confirmationService.validateLinkCode(newUser.confirmation, linkCode)
    newUser.registrationStatus = RegistrationStatus.PERSONAL_DATA_AWAIT
    newUser.continuationCode = UUID.randomUUID()
  }
}