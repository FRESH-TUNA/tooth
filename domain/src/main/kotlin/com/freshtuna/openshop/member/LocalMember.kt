package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role
import java.util.regex.Pattern

class LocalMember(
    id: String?,
    nickname: String?,
    roles: List<Role>?,
    var localId: String?,
    var password: Password?,
) : Member(id, nickname, roles) {

    companion object {
        private val PASSWORD_SATETY_REGEX = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}!%^&+=])(?=\\S+${'$'}).{8,20}${'$'}")

        fun ofId(id: String): LocalMember {
            return LocalMember(id, null, null, null, null)
        }
    }

    /**
     * 패스워드가 안전한지 검증한다.
     */
    fun checkPasswordRule(): Boolean {
        return password!!.checkPasswordRule()
    }

    /**
     * 패스워드가 맞는지 검사한다.
     * 패스워드가 틀리면 예외를 발생시킨다.
     */
    fun checkPassword(password: Password): Boolean =
        this.password!!.isSamePassword(password)
}
