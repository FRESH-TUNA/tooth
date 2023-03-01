package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.member.Password

interface SignInJWTUseCase {

    /**
     * 로컬id와 패스워드로 로그인을 시도 한다.
     * 성공하면 로그인에 성공하면 해당 멤버를 반환한다.
     */
    fun signIn(localId: String, password: Password): JWTResult
}
