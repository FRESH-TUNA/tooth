package com.freshtuna.openshop.endpoint.external.auth.request

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.auth.command.SignInCommand

class SignInRequest(
    val id: String,
    val password: String
) {
    fun toCommand() = SignInCommand(id, Password(password))
}
