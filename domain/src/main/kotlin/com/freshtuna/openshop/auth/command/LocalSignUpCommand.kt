package com.freshtuna.openshop.auth.command

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.id.LocalId

class LocalSignUpCommand(
    val localId: LocalId,
    val password: Password,
    val repeatPassword: Password
) {
    val roles = Role.OF_NEW_MEMBER
}
