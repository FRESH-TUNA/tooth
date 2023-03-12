package com.freshtuna.openshop.member.outgoing

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password

import com.freshtuna.openshop.member.constant.Provider
import com.freshtuna.openshop.member.id.LocalId
import com.freshtuna.openshop.member.id.PublicId

interface MemberSearchPort {
    /**
     * 이미같은 localId로 가입한 로컬 계정이 있는지 검사한다.
     */
    fun existsLocalMember(id: LocalId) : Boolean

    /**
     * localId와 패스워드로 가입한 로컬 계정을 반환한다.
     */
    fun findLocalMember(id: LocalId) : LocalMember

    /**
     * publicId 패스워드로 가입한 로컬 계정을 반환한다.
     */
    fun findLocalMember(id: PublicId) : LocalMember

    /**
     * 같은 정보로 가입된 소셜계정이 있는지 검사한다.
     */
    fun existsOAuthMember(oauthId: String, provider: Provider) : Boolean

    fun findLocalMemberByIdAndPassword(localId: String, password: Password): LocalMember
}
