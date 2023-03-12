package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.auth.result.JWTLocalSignInResult
import com.freshtuna.openshop.auth.command.LocalSignUpCommand

interface JWTSignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUp(command: LocalSignUpCommand): JWTLocalSignInResult
}
