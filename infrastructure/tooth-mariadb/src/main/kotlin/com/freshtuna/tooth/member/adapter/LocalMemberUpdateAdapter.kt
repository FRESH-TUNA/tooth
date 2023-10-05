package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.member.LocalMember

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import com.freshtuna.tooth.member.outgoing.LocalMemberUpdatePort
import io.github.oshai.KotlinLogging
import org.springframework.stereotype.Component

@Component
class LocalMemberUpdateAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalMemberUpdatePort {


    override fun changePassword(member: LocalMember, newPassword: Password) {

        val optionalOfLocalMember = localMemberRepository.findById(member.id.longID())

        if(!optionalOfLocalMember.isPresent)
            Oh.localMemberNotExisted()

        optionalOfLocalMember.get().changePassword(newPassword)
    }
}
