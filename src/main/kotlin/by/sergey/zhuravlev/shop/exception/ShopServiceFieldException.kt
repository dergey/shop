package by.sergey.zhuravlev.shop.exception

import by.sergey.zhuravlev.shop.model.ErrorCode

class ShopServiceFieldException(val field: String, val code: ErrorCode): RuntimeException() {
}