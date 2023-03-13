package com.freshtuna.openshop.auth

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.incoming.LocalSignInUseCase
import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.auth.result.SignInResult
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.auth.command.LocalSignInCommand
import com.freshtuna.openshop.auth.result.SignInJWTResult
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LocalSignInComposite(
    private val memberSearchPort: MemberSearchPort,
    private val jwtUseCase: JWTUseCase,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : LocalSignInUseCase {
    override fun signIn(command: LocalSignInCommand): SignInResult {

        val member = memberSearchPort.findLocalMember(command.localId)

        if(!securedPasswordUseCase.matched(command.password, member.password))
            Oh.localAuthenticationFail()

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return SignInJWTResult(member, accessToken, refreshToken)
    }
}
