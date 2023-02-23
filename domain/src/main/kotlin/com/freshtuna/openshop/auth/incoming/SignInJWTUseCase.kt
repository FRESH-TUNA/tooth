package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.jwt.JWTResult

interface SignInJWTUseCase {
    /**
     * 로컬id와 패스워드로 로그인을 시도 한다.
     * 성공하면 로그인에 성공하면 해당 멤버를 반환한다.
     */
    fun signIn(localId: String, password: String): JWTResult
}

// id, password 검증 -> accessToken, refreshToken 생성 -> MemberSignInJWTLocalResult 반환
// 포트는 도메인을 리턴한다.
