package com.freshtuna.tooth.token


/**
 * 인증에 필요한 정보를 담고 있는 객체
 */
class AuthToken(val tokenString: String) {

    companion object {
        fun of(tokenString: String): AuthToken
            = AuthToken(tokenString)
    }
}
