package com.freshtuna.openshop.exception

import com.freshtuna.openshop.exception.constant.Error

class OpenMsgException(error: Error, val msg: String): OpenException(error)