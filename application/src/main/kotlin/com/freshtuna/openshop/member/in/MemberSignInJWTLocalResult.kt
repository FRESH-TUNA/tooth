package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.LocalMember

class MemberSignInJWTLocalResult(
    val member: LocalMember,
    val accessToken: String,
    val refreshToken: String,
) {
}