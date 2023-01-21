package com.freshtuna.openshop

import java.util.regex.Pattern

class LocalMember(
    id: String,
    name: String,
    nickname: String,
    roles: List<Role>,
    var localId: String,
    var password: String,
) : Member(id, name, nickname, roles) {

    companion object {
        private val PASSWORD_SATETY_REGEX = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}!%^&+=])(?=\\S+${'$'}).{8,20}${'$'}")
    }

    /**
     * 패스워드가 안전한지 검증한다.
     */
    fun checkPasswordRule(): Boolean {
        return PASSWORD_SATETY_REGEX.matcher(this.password).matches()
    }
}
