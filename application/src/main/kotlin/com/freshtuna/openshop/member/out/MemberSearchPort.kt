package com.freshtuna.openshop.member.out

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.OAuthProvider

interface MemberSearchPort {
    /**
     * 이미같은 localId로 가입한 로컬 계정이 있는지 검사한다.
     */
    fun existsLocalMemberBylocalId(localId: String) : Boolean

    /**
     * localId로 가입한 로컬 계정을 반환한다.
     * 없므면 null을 반환한다.
     */
    fun findLocalMemberBylocalId(localId: String) : LocalMember?

    /**
     * 같은 정보로 가입된 소셜계정이 있는지 검사한다.
     */
    fun existsOAuthMember(oauthId: String, provider: OAuthProvider) : Boolean
}
