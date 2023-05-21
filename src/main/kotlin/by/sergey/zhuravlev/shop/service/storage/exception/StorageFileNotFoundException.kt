package by.sergey.zhuravlev.shop.service.storage.exception

class StorageFileNotFoundException : RuntimeException {
  constructor(message: String?) : super(message)
  constructor(message: String?, cause: Throwable?) : super(message, cause)
}