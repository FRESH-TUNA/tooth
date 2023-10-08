package com.freshtuna.tooth.auth

import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.auth.incoming.SignUpUseCase

import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort
import com.freshtuna.tooth.member.outgoing.NewLocalMemberPort

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.id.incoming.LocalIdValidateUseCase
import com.freshtuna.tooth.token.result.AuthResult
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SignUpComposite(
    private val newLocalMemberPort: NewLocalMemberPort,
    private val localMemberSearchPort: LocalMemberSearchPort,

    private val tokenManager: ManageTokenUseCase,
    private val refreshTokenManager: ManageTokenUseCase,

    private val securedPasswordUseCase: SecuredPasswordUseCase,
    private val localIdValidateUseCase: LocalIdValidateUseCase
) : SignUpUseCase {

    override fun signUp(command: SignUpCommand): AuthResult {
        localIdValidateUseCase.validate(command.localId)

        if (!command.password.checkPasswordRule())
            Oh.breakPasswordRule()

        if (localMemberSearchPort.existsByLocalId(command.localId))
            Oh.localIdUsed(command.localId)

        securedPasswordUseCase.encrypt(command.password)

        val member = newLocalMemberPort.new(command)

        val accessToken = tokenManager.generate(member)
        val refreshToken = refreshTokenManager.generate(member)

        return AuthResult(accessToken, refreshToken)
    }
}
