package com.freshtuna.openshop.member

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.incoming.ChangePasswordUseCase
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import com.freshtuna.openshop.member.outgoing.MemberSearchPort

class PasswordChangeService(
    private val memberUpdatePort: LocalMemberUpdatePort,
    private val memberSearchPort: MemberSearchPort,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : ChangePasswordUseCase {

    override fun changePassword(id: String, curPassword: Password, newPassword: Password) {
        val member = memberSearchPort.findLocalMember(id, securedPasswordUseCase.generate(curPassword))

        if (!newPassword.checkPasswordRule())
            Oh.breakPasswordRule()

        memberUpdatePort.changePassword(member, securedPasswordUseCase.generate(newPassword))
    }
}
