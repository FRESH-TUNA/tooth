package com.freshtuna.openshop.endpoint.external.auth.request

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.auth.command.LocalSignInCommand
import com.freshtuna.openshop.id.LocalId

class SignInRequest(
    val id: String,
    val password: String
) {
    fun toCommand() = LocalSignInCommand(LocalId(id), Password(password))
}
