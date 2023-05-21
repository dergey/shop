package by.sergey.zhuravlev.shop.service.storage.exception

class StorageException : RuntimeException {
  constructor(message: String?) : super(message)
  constructor(message: String?, cause: Throwable?) : super(message, cause)
}