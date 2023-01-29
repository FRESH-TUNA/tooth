package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.OAuthMember
import com.freshtuna.openshop.member.out.MemberSearchPort
import com.freshtuna.openshop.member.out.MemberSignUpPort
import com.freshtuna.openshop.member.out.MemberUpdatePort

class MemberSignUpService(
    private val memberSignUpPort: MemberSignUpPort,
    private val memberSearchPort: MemberSearchPort,
    private val memberUpdatePort: MemberUpdatePort
) : MemberSignUpUseCase {

    override fun signUpLocalMember(member: LocalMember) {

        checkExistedLocalId(member)

        memberSignUpPort.signUp(member)
    }

    override fun signUpOAuthMember(member: OAuthMember) {
        // 이미 멤버가 존재하면 새로운 정보로 업데이트 한다.
        if (memberSearchPort.existsOAuthMember(member.oauthId, member.provider))
            memberUpdatePort.updateMember(member)

        // 멤버가 없으면 저장 진행
        memberSignUpPort.signUp(member)
    }

    private fun checkExistedLocalId(member: LocalMember) {
        if (memberSearchPort.existsLocalMemberBylocalId(member.localId))
            throw RuntimeException("이미 같은 아이디를 가지는 계정이 있음")
    }
}
