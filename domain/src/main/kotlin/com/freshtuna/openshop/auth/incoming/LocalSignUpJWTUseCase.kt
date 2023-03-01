package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password

interface LocalSignUpJWTUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUp(member: LocalMember, password: Password): JWTResult
}
