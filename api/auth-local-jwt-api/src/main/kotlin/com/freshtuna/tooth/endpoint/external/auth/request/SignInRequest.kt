package com.freshtuna.tooth.endpoint.external.auth.request

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.auth.command.SignInCommand
import com.freshtuna.tooth.id.ID

class SignInRequest(
    val id: String,
    val password: String
) {
    fun toCommand() = SignInCommand(ID(id), Password(password))
}
