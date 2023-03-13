package com.freshtuna.openshop.member.incoming

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.id.PublicId

interface ChangePasswordUseCase {

    /**
     * password를 변경한다.
     */
    fun changePassword(id: PublicId, curPassword: Password, newPassword: Password)
}
