package com.freshtuna.openshop.member.incoming

import com.freshtuna.openshop.member.Password

interface ChangePasswordUseCase {

    /**
     * password를 변경한다.
     */
    fun changePassword(id: String, curPassword: Password, newPassword: Password)
}
