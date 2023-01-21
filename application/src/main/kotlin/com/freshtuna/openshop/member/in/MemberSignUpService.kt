package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.OAuthMember
import com.freshtuna.openshop.member.out.MemberSearchPort
import com.freshtuna.openshop.member.out.MemberSignUpPort

class MemberSignUpService(
    private val memberSignUpPort: MemberSignUpPort,
    private val memberSearchPort: MemberSearchPort,
) : MemberSignUpUseCase {

    override fun signUpLocalMember(member: LocalMember) {

        checkExistedLocalId(member)

        memberSignUpPort.signUp(member)
    }

    override fun signUpOAuthMember(member: OAuthMember) {
        //not implemented
    }

    private fun checkExistedLocalId(member: LocalMember) {
        if (memberSearchPort.existsLocalMemberBylocalId(member.localId))
            throw RuntimeException("이미 같은 아이디를 가지는 계정이 있음")
    }
}
