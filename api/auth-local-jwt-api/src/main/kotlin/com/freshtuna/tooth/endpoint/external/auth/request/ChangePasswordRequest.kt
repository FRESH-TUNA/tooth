package com.freshtuna.tooth.endpoint.external.auth.request

class ChangePasswordRequest (
    val curPassword: String,
    val newPassword: String,
    val newPasswordRepeat: String,
)
