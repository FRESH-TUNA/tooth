package com.freshtuna.openshop.exception

import com.freshtuna.openshop.exception.constant.Oh

open class OpenMsgException(oh: Oh, val msg: String): OpenException(oh)