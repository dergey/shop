package by.sergey.zhuravlev.shop.exception

import by.sergey.zhuravlev.shop.model.ErrorCode

class ShopServiceException(val code: ErrorCode): RuntimeException() {
}