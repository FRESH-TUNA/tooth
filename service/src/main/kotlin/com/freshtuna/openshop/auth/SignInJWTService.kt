package com.freshtuna.openshop.auth

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.jwt.incoming.JWTUseCase

class SignInJWTService(
    private val memberSearchPort: MemberSearchPort,
    private val jwtUseCase: JWTUseCase
) : SignInJWTUseCase {
    override fun signInLocalMember(localId: String, password: String): JWTResult {

        val member: LocalMember = memberSearchPort.findLocalMemberByIdAndPassword(localId, password)

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return JWTResult(member, accessToken, refreshToken)
    }
}
