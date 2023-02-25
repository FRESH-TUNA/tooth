package com.freshtuna.openshop.endpoint.external.request

class ChangePasswordRequest (
    val curPassword: String,
    val newPassword: String,
    val newPasswordRepeat: String,
)
