package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import java.util.*

class LocalMemberUpdateAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalMemberUpdatePort {

    override fun changePassword(publicId: String, newPassword: String) {

       val dbMember = localMemberRepository.findByPublicId(UUID.fromString(publicId))

       dbMember.changePassword(newPassword)
    }
}
