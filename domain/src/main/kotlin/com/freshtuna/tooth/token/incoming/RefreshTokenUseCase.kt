package com.freshtuna.tooth.token.incoming

import com.freshtuna.tooth.token.command.RefreshTokenCommand
import com.freshtuna.tooth.token.result.RefreshResult

interface RefreshTokenUseCase {

    /**
     * 토큰을 재발행한다.
     */
    fun refresh(command: RefreshTokenCommand): RefreshResult
}