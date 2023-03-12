package com.freshtuna.openshop.auth.result

import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.member.Member

class JWTLocalSignInResult(
    member: Member,
    val accessToken: JWT,
    val refreshToken: JWT,
) : LocalSignInResult(member)
