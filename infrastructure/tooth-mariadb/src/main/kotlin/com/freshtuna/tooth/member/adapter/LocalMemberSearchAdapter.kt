package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.id.ID

import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository

import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort

import org.springframework.stereotype.Component

@Component
class LocalMemberSearchAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalMemberSearchPort {

    override fun existsByLocalId(localId: ID): Boolean {
        return localMemberRepository.existsByLocalId(localId.stringID())
    }

    override fun findByLocalId(localId: ID): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findByLocalId(localId.stringID())

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted(localId)

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun findByPublicID(id: ID): LocalMember {
        val optionalOfLocalMember = localMemberRepository.findByPublicId(id.uuid())

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        return optionalOfLocalMember.get().toLocalMember()
    }

    override fun findBy(id: ID): LocalMember {

        val optionalOfLocalMember = localMemberRepository.findById(id.longID())

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        return optionalOfLocalMember.get().toLocalMember()
    }
}
