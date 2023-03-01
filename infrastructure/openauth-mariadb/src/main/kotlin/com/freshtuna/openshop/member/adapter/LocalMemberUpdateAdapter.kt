package com.freshtuna.openshop.member.adapter

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.SecuredPassword
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import java.util.*

class LocalMemberUpdateAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalMemberUpdatePort {

    override fun changePassword(member: LocalMember, newPassword: SecuredPassword) {
        val dbMember = localMemberRepository.findByPublicId(UUID.fromString(member.id))
        dbMember.changePassword(newPassword)
    }
}
