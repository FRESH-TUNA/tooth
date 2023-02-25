package com.freshtuna.openshop.member.incoming

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password

interface ChangePasswordUseCase {

    /**
     * password를 변경한다.
     */
    fun changePassword(member: LocalMember, curPassword: Password, newPassword: Password)
}
