package com.freshtuna.openshop.member.adapter

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.EncryptedPassword
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.member.constant.Provider
import com.freshtuna.openshop.id.LocalId
import com.freshtuna.openshop.id.PublicId
import org.springframework.stereotype.Component
import java.util.*

@Component
class MemberSearchAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : MemberSearchPort {

    override fun existsLocalMember(localId: LocalId): Boolean {
        return localMemberRepository.existsByLocalId(localId.toString())
    }

    override fun findLocalMember(localId: LocalId): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(localId.toString())

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(localId)

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun findLocalMember(id: PublicId): LocalMember {
        val optionalOfLocalMember = localMemberRepository.findByPublicId(UUID.fromString(id.toString()))

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun existsOAuthMember(oauthId: String, provider: Provider): Boolean {
        TODO("Not yet implemented")
    }

    override fun findLocalMemberByIdAndPassword(localId: String, password: Password): LocalMember {
        TODO("Not yet implemented")
    }
}
