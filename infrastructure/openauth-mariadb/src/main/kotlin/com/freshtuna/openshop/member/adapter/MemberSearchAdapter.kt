package com.freshtuna.openshop.member.adapter

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
import com.freshtuna.openshop.member.repository.MariaDBMemberRepository
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.member.constant.Provider

class MemberSearchAdapter(
    private val memberRepository: MariaDBMemberRepository,
    private val localMemberRepository: MariaDBLocalMemberRepository
) : MemberSearchPort {

    override fun existsLocalMemberBylocalId(localId: String): Boolean {

        return localMemberRepository.existsByLocalId(localId)
    }

    override fun findLocalMember(localId: String): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(localId)

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(localId)

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun findLocalMember(member: LocalMember, password: Password): LocalMember {
        TODO("Not yet implemented")
    }

    override fun existsOAuthMember(oauthId: String, provider: Provider): Boolean {
        TODO("Not yet implemented")
    }

    override fun findLocalMemberByIdAndPassword(localId: String, password: Password): LocalMember {
        TODO("Not yet implemented")
    }
}
