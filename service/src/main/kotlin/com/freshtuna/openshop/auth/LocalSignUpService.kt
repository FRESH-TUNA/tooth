package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.incoming.LocalSignUpUseCase
import com.freshtuna.openshop.member.LocalMember

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort

class LocalSignUpService(
    private val localSignUpPort: LocalSignUpPort,
    private val memberSearchPort: MemberSearchPort
) : LocalSignUpUseCase {

    override fun signUpLocalMember(member: LocalMember) {
        if (memberSearchPort.findLocalMemberBylocalId(member.localId) != null)
            throw RuntimeException("이미 같은 아이디를 가지는 계정이 있음")

        localSignUpPort.signUp(member)
    }
}
