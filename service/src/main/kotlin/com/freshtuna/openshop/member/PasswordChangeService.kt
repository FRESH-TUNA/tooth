package com.freshtuna.openshop.member

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.incoming.ChangePasswordUseCase
import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import com.freshtuna.openshop.member.outgoing.MemberSearchPort

class PasswordChangeService(
    private val memberUpdatePort: LocalMemberUpdatePort,
    private val memberSearchPort: MemberSearchPort
) : ChangePasswordUseCase {

    override fun changePassword(member: LocalMember, curPassword: Password, newPassword: Password) {
        val member = memberSearchPort.findLocalMember(member, curPassword)

        if (!newPassword.checkPasswordRule())
            Oh.breakPasswordRule()

        memberUpdatePort.changePassword(member, newPassword)
    }
}
