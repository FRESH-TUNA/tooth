package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.incoming.LocalSignUpJWTUseCase
import com.freshtuna.openshop.member.LocalMember

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.jwt.incoming.JWTUseCase

class LocalSignUpJWTService(
    private val localSignUpPort: LocalSignUpPort,
    private val memberSearchPort: MemberSearchPort,
    private val jwtUseCase: JWTUseCase
) : LocalSignUpJWTUseCase {

    override fun signUp(member: LocalMember): JWTResult {
        if (memberSearchPort.existsLocalMemberBylocalId(member.localId!!))
            Oh.localIdUsed(member.localId!!)

        val member = localSignUpPort.signUp(member)

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return JWTResult(member, accessToken, refreshToken)
    }
}
