package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.incoming.LocalSignUpJWTUseCase
import com.freshtuna.openshop.member.LocalMember

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LocalSignUpJWTService(
    private val localSignUpPort: LocalSignUpPort,
    private val memberSearchPort: MemberSearchPort,
    private val jwtUseCase: JWTUseCase,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : LocalSignUpJWTUseCase {

    override fun signUp(member: LocalMember, password: Password): JWTResult {
        if (memberSearchPort.existsLocalMember(member.localId!!))
            Oh.localIdUsed(member.localId!!)

        if (!password.checkPasswordRule())
            Oh.breakPasswordRule()

        val member = localSignUpPort.signUp(member, securedPasswordUseCase.generate(password))

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return JWTResult(member, accessToken, refreshToken)
    }
}
