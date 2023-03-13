package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.command.LocalSignUpCommand
import com.freshtuna.openshop.auth.incoming.JWTSignUpUseCase

import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.auth.result.JWTLocalSignInResult
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class JWTSignUpComposite(
    private val localSignUpPort: LocalSignUpPort,
    private val memberSearchPort: MemberSearchPort,

    private val jwtUseCase: JWTUseCase,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : JWTSignUpUseCase {

    override fun signUp(command: LocalSignUpCommand): JWTLocalSignInResult {
        if(!command.localId.checkRule())
            Oh.breakLocalIdRule()

        if (!command.password.checkPasswordRule())
            Oh.breakPasswordRule()

        if (memberSearchPort.existsLocalMember(command.localId))
            Oh.localIdUsed(command.localId)

        val member = localSignUpPort.signUp(command, securedPasswordUseCase.generate(command.password))

        val accessToken = jwtUseCase.generateAccessToken(member)
        val refreshToken = jwtUseCase.generateRefreshToken(member)

        return JWTLocalSignInResult(member, accessToken, refreshToken)
    }
}
