package com.freshtuna.tooth.endpoint.external.auth.request

import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.id.ID

class SignUpRequest(
    val id: String,
    val nickname: String?,
    val password: String,
    private val repeatPassword: String
) {
    fun toCommand(): SignUpCommand
        = SignUpCommand(ID(id), Password(password), Password(repeatPassword))
}
