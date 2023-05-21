package by.sergey.zhuravlev.shop.domain

enum class RegistrationStatus {
  EMAIL_CONFIRMATION,
  PHONE_CONFIRMATION,
  PERSONAL_DATA_AWAIT,
  FAIL
}