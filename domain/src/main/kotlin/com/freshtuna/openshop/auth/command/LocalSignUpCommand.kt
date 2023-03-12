package com.freshtuna.openshop.auth.command

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.constant.Role

class LocalSignUpCommand(
    val localId: String,
    val password: Password,
    val repeatPassword: Password
) {
    val roles = listOf(Role.USER)
}
