package com.freshtuna.openshop

class OpenMsgException(error: Error, val msg: String): OpenException(error)