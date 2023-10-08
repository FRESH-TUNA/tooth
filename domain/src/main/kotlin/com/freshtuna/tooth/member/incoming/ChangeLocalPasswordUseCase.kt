package com.freshtuna.tooth.member.incoming

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.Password

interface ChangeLocalPasswordUseCase {

    /**
     * password를 변경한다.
     */
    fun change(id: ID, curPassword: Password, newPassword: Password)
}
