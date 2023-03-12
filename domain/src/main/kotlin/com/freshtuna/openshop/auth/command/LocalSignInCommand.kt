package com.freshtuna.openshop.auth.command

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.id.LocalId

class LocalSignInCommand(
    val localId: LocalId,
    val password: Password
)
