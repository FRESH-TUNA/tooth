package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.auth.result.JWTLocalSignInResult
import com.freshtuna.openshop.auth.command.LocalSignInCommand

interface JWTSignInUseCase {

    /**
     * 로컬id와 패스워드로 로그인을 시도 한다.
     * 성공하면 로그인에 성공하면 해당 멤버를 반환한다.
     */
    fun signIn(command: LocalSignInCommand): JWTLocalSignInResult
}
