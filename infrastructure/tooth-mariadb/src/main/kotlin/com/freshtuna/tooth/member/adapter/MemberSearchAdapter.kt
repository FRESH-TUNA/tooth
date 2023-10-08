package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.Member
import com.freshtuna.tooth.member.outgoing.MemberSearchPort

import com.freshtuna.tooth.member.repository.MariaDBMemberRepository
import org.springframework.stereotype.Component

@Component
class MemberSearchAdapter(
    private val memberRepository: MariaDBMemberRepository
) : MemberSearchPort {

    override fun findByPublicID(id: ID): Member {
        return memberRepository.findByPublicId(id.uuid()).toMember()
    }
}
