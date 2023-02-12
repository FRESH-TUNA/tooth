package com.freshtuna.openshop.jwt.incoming

import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.member.Member

interface JWTUseCase {
    /**
     * 액새스 토큰을 발행한다.
     */
    fun generateAccessToken(member: Member): JWT

    /**
     * 리프레시 토큰을 발행한다.
     */
    fun generateRefreshToken(member: Member): JWT

    /**
     * 토큰을 검증한다.
     */
    fun isValid(token: JWT): Boolean

    /**
     * 토큰에서 id를 추출한다.
     */
    fun idOfToken(token: JWT): String
}
