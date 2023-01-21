package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.OAuthMember

interface MemberSignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUpLocalMember(member: LocalMember)

    /**
     * OAuth 회원가입을 진행ㅎㄴ다.
     */
    fun signUpOAuthMember(member: OAuthMember)
}
