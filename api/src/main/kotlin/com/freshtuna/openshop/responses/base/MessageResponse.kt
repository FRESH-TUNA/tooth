package com.freshtuna.openshop.responses.base

class MessageResponse(
    code : String,
    val message : String
) : BasicResponse(code)
