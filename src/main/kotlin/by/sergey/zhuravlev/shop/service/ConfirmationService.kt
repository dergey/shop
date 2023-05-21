package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.domain.Confirmation
import by.sergey.zhuravlev.shop.domain.ConfirmationType
import by.sergey.zhuravlev.shop.exception.ShopServiceException
import by.sergey.zhuravlev.shop.exception.ShopServiceFieldException
import by.sergey.zhuravlev.shop.model.ErrorCode
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ConfirmationService(
  @Value("\${confirmation.code.manual.length:5}")
  private val manualCodeLength: Int,
  @Value("\${confirmation.code.link.length:16}")
  private val linkCodeLength: Int,
  @Value("\${confirmation.code.manual.max-tries:3}")
  private val manualCodeMaxTries: Int,
  @Value("\${confirmation.test:false}")
  private val isTest: Boolean
) {

  fun createPhoneConfirmation(): Confirmation {
    return Confirmation(
      ConfirmationType.PHONE,
      generateManualCode(),
      0,
      null,
      LocalDateTime.now().plusMinutes(15)
    )
  }

  fun createEmailConfirmation(): Confirmation {
    return Confirmation(
      ConfirmationType.EMAIL,
      generateManualCode(),
      0,
      generateLinkCode(),
      LocalDateTime.now().plusHours(6)
    )
  }

  fun resetConfirmation(confirmation: Confirmation) {
    confirmation.manualCode = generateManualCode()
    confirmation.manualCodeTries = 0
    if (confirmation.type == ConfirmationType.EMAIL) {
      confirmation.linkCode = generateLinkCode()
      confirmation.validUntil = LocalDateTime.now().plusHours(6)
    } else {
      confirmation.validUntil = LocalDateTime.now().plusMinutes(15)
    }
  }

  fun isActualConfirmation(confirmation: Confirmation): Boolean {
    return confirmation.validUntil.isBefore(LocalDateTime.now())
        && confirmation.manualCodeTries < manualCodeMaxTries
  }

  fun validateManualCode(confirmation: Confirmation, code: String?) {
    if (confirmation.validUntil.isBefore(LocalDateTime.now())) {
      throw ShopServiceException(ErrorCode.CONFIRMATION_HAS_EXPIRED)
    }
    if (confirmation.manualCode != code) {
      if (confirmation.manualCodeTries + 1 > manualCodeMaxTries) {
        throw ShopServiceException(ErrorCode.TOO_MANY_CONFIRMATION_TRIES)
      }
      confirmation.manualCodeTries++
      throw ShopServiceFieldException("manualCode", ErrorCode.NOT_VALID)
    }
  }

  fun validateLinkCode(confirmation: Confirmation, code: String?) {
    if (confirmation.validUntil.isBefore(LocalDateTime.now())) {
      throw ShopServiceException(ErrorCode.CONFIRMATION_HAS_EXPIRED)
    }
    // The code will always be correct, because the search goes by the code.
  }

  private fun generateManualCode(): String {
    return if (isTest) {
      "1".repeat(manualCodeLength)
    } else {
      RandomStringUtils.randomAlphanumeric(manualCodeLength)
    }
  }

  private fun generateLinkCode(): String {
    return RandomStringUtils.randomAlphanumeric(linkCodeLength)
  }
}