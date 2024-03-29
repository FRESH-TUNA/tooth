package com.freshtuna.tooth.member.command

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.ID

class SignUpCommand(
    val localId: ID,
    val password: Password,
    private val repeatPassword: Password
) {
    val roles = Role.OF_NEW_MEMBER

    init {
        passwordSameCheck()
    }

    /**
     * validations
     */
    private fun passwordSameCheck() {
        if(password.passwordString != repeatPassword.passwordString)
            Oh.repeatPasswordNotEqual()
    }
}
