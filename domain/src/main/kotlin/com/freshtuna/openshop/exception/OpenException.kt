package com.freshtuna.openshop.exception

import com.freshtuna.openshop.exception.constant.Error

open class OpenException(val error: Error): RuntimeException() {

}
