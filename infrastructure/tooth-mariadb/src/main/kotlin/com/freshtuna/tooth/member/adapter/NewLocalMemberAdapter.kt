package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.member.outgoing.NewLocalMemberPort
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.entity.MariaDBLocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import org.springframework.stereotype.Component

@Component
class NewLocalMemberAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : NewLocalMemberPort {

    override fun new(command: SignUpCommand): LocalMember {
        val newMember = MariaDBLocalMember.of(command)

        localMemberRepository.save(newMember)

        return newMember.toLocalMember()
    }
}
