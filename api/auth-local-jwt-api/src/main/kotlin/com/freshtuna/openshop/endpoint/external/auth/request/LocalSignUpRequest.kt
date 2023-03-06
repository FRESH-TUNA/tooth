package com.freshtuna.openshop.endpoint.external.auth.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.constant.Role

class LocalSignUpRequest(
    val id: String,
    val nickname: String?,
    val password: String,
    val repeatPassword: String
) {
    fun toNewMember(): LocalMember {
        return LocalMember(null, nickname, Role.ofNewMember(), id)
    }

    fun toPassword(): Password {
        return Password(password)
    }
}
