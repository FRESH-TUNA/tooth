package com.freshtuna.tooth.auth

import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort
import com.freshtuna.tooth.auth.incoming.SignInUseCase
import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.token.result.AuthResult
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.auth.command.SignInCommand

import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import io.github.oshai.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SignInComposite(
    private val localMemberSearchPort: LocalMemberSearchPort,
    private val securedPasswordUseCase: SecuredPasswordUseCase,
    private val tokenManager: ManageTokenUseCase,
    private val refreshTokenManager: ManageTokenUseCase,
) : SignInUseCase {

    private val log = KotlinLogging.logger {}

    override fun signIn(command: SignInCommand): AuthResult {

        val member = localMemberSearchPort.findByLocalId(command.localId)
        log.debug(member.id.stringID())

        if(!securedPasswordUseCase.matched(command.password, member.password))
            Oh.localAuthenticationFail()
        log.debug("pass")

        val accessToken = tokenManager.generate(member)
        val refreshToken = refreshTokenManager.generate(member)

        log.debug(accessToken.tokenString)
        log.debug(refreshToken.tokenString)
        return AuthResult(accessToken, refreshToken)
    }
}
