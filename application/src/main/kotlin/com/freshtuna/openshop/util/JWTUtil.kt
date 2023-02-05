package com.freshtuna.openshop.util

import com.freshtuna.openshop.Member

interface JWTUtil {
    /**
     * 액새스 토큰을 발행한다.
     */
    fun generateAccessToken(member: Member): String

    /**
     * 리프레시 토큰을 발행한다.
     */
    fun generateRefreshToken(member: Member): String

    /**
     * 토큰을 검증한다.
     */
    fun isValid(token: String): Boolean

    /**
     * 토큰에서 id를 추출한다.
     */
    fun idOfToken(token: String): String?
}
