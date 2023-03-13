package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.auth.result.SignInResult
import com.freshtuna.openshop.auth.command.LocalSignUpCommand

interface LocalSignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUp(command: LocalSignUpCommand): SignInResult
}
