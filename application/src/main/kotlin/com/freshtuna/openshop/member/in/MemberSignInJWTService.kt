package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.Error
import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.OpenException
import com.freshtuna.openshop.member.out.MemberSearchPort
import com.freshtuna.openshop.util.JWTUtil
import java.util.Objects

class MemberSignInJWTService(
    private val memberSearchPort: MemberSearchPort,
    private val jwtUtil: JWTUtil,
) : MemberSignInJWTUseCase {
    override fun signInLocalMember(localId: String, password: String): MemberSignInJWTLocalResult {
        val member : LocalMember? = memberSearchPort.findLocalMemberBylocalId(localId)

        if (Objects.isNull(member))
            throw OpenException(Error.LOCAL_MEMBER_NOT_FOUNDED)

        member!!.checkPassword(password)

        val accessToken = jwtUtil.generateAccessToken(member)
        val refreshToken = jwtUtil.generateRefreshToken(member)

        return MemberSignInJWTLocalResult(member, accessToken, refreshToken)
    }
}
