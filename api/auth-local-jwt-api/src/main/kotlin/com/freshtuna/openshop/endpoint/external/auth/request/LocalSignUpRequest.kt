package com.freshtuna.openshop.endpoint.external.auth.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.constant.Role

class LocalSignUpRequest(
    @JsonProperty("id") val id: String,
    @JsonProperty("nickname") val nickname: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("repeatPassword") val repeatPassword: String
) {
    fun toNewMember(): LocalMember {
        return LocalMember(null, nickname, Role.ofNewMember(), id, password)
    }
}
