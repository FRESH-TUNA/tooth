package com.freshtuna.openshop.jwt

import com.freshtuna.openshop.member.Member

class JWTResult(
    val member: Member,
    val accessToken: JWT,
    val refreshToken: JWT,
)
