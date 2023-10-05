package com.freshtuna.tooth.auth.command

import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.id.ID

class SignInCommand(
    val localId: ID,
    val password: Password
)
