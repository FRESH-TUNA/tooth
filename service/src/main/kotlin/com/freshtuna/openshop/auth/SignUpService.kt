package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.incoming.SignUpUseCase
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.OAuthMember
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.member.outgoing.MemberSignUpPort
import com.freshtuna.openshop.member.outgoing.MemberUpdatePort

class SignUpService(
    private val memberSignUpPort: MemberSignUpPort,
    private val memberSearchPort: MemberSearchPort,
    private val memberUpdatePort: MemberUpdatePort
) : SignUpUseCase {

    override fun signUpLocalMember(member: LocalMember) {
        if (memberSearchPort.findLocalMemberBylocalId(member.localId) != null)
            throw RuntimeException("이미 같은 아이디를 가지는 계정이 있음")

        memberSignUpPort.signUp(member)
    }

    override fun signUpOAuthMember(member: OAuthMember) {
        // 이미 멤버가 존재하면 새로운 정보로 업데이트 한다.
        if (memberSearchPort.existsOAuthMember(member.oauthId, member.provider))
            memberUpdatePort.updateMember(member)

        // 멤버가 없으면 저장 진행
        memberSignUpPort.signUp(member)
    }
}
