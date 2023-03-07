package com.freshtuna.openshop.member.adapter

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.SecuredPassword
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.member.constant.Provider
import org.springframework.stereotype.Component

@Component
class MemberSearchAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : MemberSearchPort {

    override fun existsLocalMember(localId: String): Boolean {
        return localMemberRepository.existsByLocalId(localId)
    }

    override fun findLocalMember(localId: String): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(localId)

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(localId)

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun findSavedPasswordByLocalMember(member: LocalMember): SecuredPassword {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(member.localId)

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(member.localId)

        return SecuredPassword(optionalOfLocalMember.get().password)
    }

    override fun existsOAuthMember(oauthId: String, provider: Provider): Boolean {
        TODO("Not yet implemented")
    }

    override fun findLocalMemberByIdAndPassword(localId: String, password: Password): LocalMember {
        TODO("Not yet implemented")
    }
}
