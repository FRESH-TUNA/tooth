package com.freshtuna.openshop.member

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.oauth.constant.OAuthProvider

class MemberSearchAdapter(
    private val memberRepository: MariaDBMemberRepository,
    private val localMemberRepository: MariaDBLocalMemberRepository
) : MemberSearchPort {

    override fun existsLocalMemberBylocalId(localId: String): Boolean {

        return localMemberRepository.existsByLocalId(localId)
    }

    override fun findLocalMemberBylocalId(localId: String): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(localId)

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(localId)

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun existsOAuthMember(oauthId: String, provider: OAuthProvider): Boolean {
        TODO("Not yet implemented")
    }

    override fun findLocalMemberByIdAndPassword(localId: String, password: String): LocalMember {
        TODO("Not yet implemented")
    }
}
