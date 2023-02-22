package com.freshtuna.openshop.member

import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort

class LocalSignUpAdapter(
    private val localMemberRepository: MariaDBLocalMemberRepository
) : LocalSignUpPort {

    override fun signUp(localMember: LocalMember): LocalMember {

        val newMember: MariaDBLocalMember = localMemberRepository.save(localMember.toMariaDBLocalMember())

        return newMember.toLocalMember()
    }
}
