package com.freshtuna.openshop.jwt

class JWT(val tokenString: String) {

    companion object {
        const val ROLE_KEY = "ROLE"
        const val PREFIX = "Bearer "
    }

    fun tokenStringWithoutPrefix(): String {
        return tokenString.removePrefix(PREFIX)
    }
}
