package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.member.LocalMember

interface LocalSignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUpLocalMember(member: LocalMember)
}
