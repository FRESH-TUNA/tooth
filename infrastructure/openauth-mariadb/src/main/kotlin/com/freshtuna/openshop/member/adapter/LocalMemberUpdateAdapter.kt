package com.freshtuna.openshop.member.adapter

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.EncryptedPassword
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import org.springframework.stereotype.Component
import java.util.*

@Component
class LocalMemberUpdateAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalMemberUpdatePort {

    override fun changePassword(member: LocalMember, newPassword: EncryptedPassword) {
        val optionalOfLocalMember = localMemberRepository
            .findByPublicId(UUID.fromString(member.publicId.toString()))

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        optionalOfLocalMember.get().changePassword(newPassword)
    }
}
