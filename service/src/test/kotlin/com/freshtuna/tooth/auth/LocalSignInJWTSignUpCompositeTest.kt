package com.freshtuna.tooth.auth

import com.freshtuna.tooth.token.AuthToken
import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.Password

import com.freshtuna.tooth.auth.command.SignInCommand
import com.freshtuna.tooth.id.ID

import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class LocalSignInJWTSignUpCompositeTest {

    private val localMemberSearchPort: LocalMemberSearchPort = mockk()
    private val tokenManager: ManageTokenUseCase = mockk()
    private val refreshTokenManager: ManageTokenUseCase = mockk()
    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val memberSignInService = SignInComposite(
        localMemberSearchPort,
        securedPasswordUseCase,
        tokenManager,
        refreshTokenManager
    )

    @Test
    @DisplayName("유저ID와 패스워드로 로그인을 시도한다")
    fun signInLocalMemberSuccess() {
        /**
         * given
         * 아이디, 비밀번호, 멤버의 데이터베이스 상의 id
         * 로그인 성공시 반환할 유저객체
         * 테스트할 서비스 객체
         */
        val localId = ID("localId")
        val password = Password("password")
        val encryptedPassword = Password("isSecured!")
        val id = ID("id")
        val member = LocalMember(id, id, Lists.emptyList(), localId, encryptedPassword)

        /**
         * when
         */
        val accessToken = AuthToken.of("accessToken!")
        val refreshToken = AuthToken.of("refreshToken!")

        every { localMemberSearchPort.findByLocalId(localId) } returns member
        every { securedPasswordUseCase.matched(password, encryptedPassword) } returns true
        every { tokenManager.generate(member) } returns accessToken
        every { refreshTokenManager.generate(member) } returns refreshToken

        /**
         * then
         */
        val result = memberSignInService.signIn(SignInCommand(localId, password))
        assertEquals(result.access, accessToken)
        assertEquals(result.refresh, refreshToken)
    }
}
