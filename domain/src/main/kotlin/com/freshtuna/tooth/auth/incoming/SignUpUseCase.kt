package com.freshtuna.tooth.auth.incoming

import com.freshtuna.tooth.token.result.AuthResult
import com.freshtuna.tooth.member.command.SignUpCommand

interface SignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUp(command: SignUpCommand): AuthResult
}
