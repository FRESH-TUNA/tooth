package com.freshtuna.openshop.auth.adapter

import com.freshtuna.openshop.auth.command.LocalSignUpCommand
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.EncryptedPassword
import com.freshtuna.openshop.member.entity.MariaDBLocalMember
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
import org.springframework.stereotype.Component

@Component
class LocalSignUpAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalSignUpPort {

    override fun signUp(command: LocalSignUpCommand, encryptedPassword: EncryptedPassword): LocalMember {
        val newMember = MariaDBLocalMember.of(command, encryptedPassword)

        localMemberRepository.save(newMember)

        return newMember.toLocalMember()
    }
}
