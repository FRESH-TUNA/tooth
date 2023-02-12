package com.freshtuna.openshop.auth.incoming

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.OAuthMember

interface SignUpUseCase {
    /**
     * 로컬 회원가입을 진행한다.
     */
    fun signUpLocalMember(member: LocalMember)

    /**
     * OAuth 회원가입을 진행ㅎㄴ다.
     */
    fun signUpOAuthMember(member: OAuthMember)
}
