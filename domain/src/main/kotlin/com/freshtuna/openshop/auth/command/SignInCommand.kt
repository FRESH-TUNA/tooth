package com.freshtuna.openshop.auth.command

import com.freshtuna.openshop.member.Password

class SignInCommand(
    val localId: String,
    val password: Password
)
