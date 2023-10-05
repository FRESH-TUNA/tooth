package com.freshtuna.tooth.token

import com.freshtuna.tooth.token.command.RefreshTokenCommand
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.token.incoming.RefreshTokenUseCase
import com.freshtuna.tooth.token.result.RefreshResult

import org.springframework.stereotype.Service

@Service
class TokenRefreshComposite(
    private val tokenManager: ManageTokenUseCase,
    private val refreshTokenManager: ManageTokenUseCase,
) : RefreshTokenUseCase {

    override fun refresh(command: RefreshTokenCommand): RefreshResult {

        val member = refreshTokenManager.identify(command.refresh)

        val newToken = tokenManager.generate(member)

        return RefreshResult(newToken)
    }
}
