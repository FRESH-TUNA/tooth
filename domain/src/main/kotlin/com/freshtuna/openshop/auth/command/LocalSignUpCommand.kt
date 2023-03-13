package com.freshtuna.openshop.auth.command

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.id.LocalId

class LocalSignUpCommand(
    val localId: LocalId,
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
        if(password != repeatPassword)
            Oh.repeatPasswordNotEqual()
    }
}
