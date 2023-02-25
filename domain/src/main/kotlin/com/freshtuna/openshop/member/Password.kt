package com.freshtuna.openshop.member

import java.util.regex.Pattern

class Password(
    private val password: String
) {

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

    fun isSamePassword(compared: Password): Boolean {
        return password == compared.password
    }
}
