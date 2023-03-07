package com.freshtuna.openshop.auth

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase
import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.SecuredPassword
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LocalSignInJWTService(
    private val memberSearchPort: MemberSearchPort,
    private val jwtUseCase: JWTUseCase,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : SignInJWTUseCase {
    override fun signIn(localId: String, password: Password): JWTResult {

        val member = memberSearchPort.findLocalMember(localId)
        val savedPW = memberSearchPort.findSavedPasswordByLocalMember(member)

        if(!securedPasswordUseCase.matched(password, savedPW))
            Oh.localAuthenticationFail()

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return JWTResult(member, accessToken, refreshToken)
    }
}
