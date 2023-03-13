package com.freshtuna.openshop.auth.result

import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.member.Member

class SignInJWTResult(
    member: Member,
    val accessToken: JWT,
    val refreshToken: JWT,
) : SignInResult(member)
