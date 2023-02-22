package com.freshtuna.openshop.member

import com.freshtuna.openshop.auth.SignInJWTService
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.jwt.incoming.JWTUseCase

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class SignInJWTServiceTest {

    private val memberSearchPort: MemberSearchPort = mockk()
    private val jwtUseCase: JWTUseCase = mockk()

    private val memberSignInService = SignInJWTService(
        memberSearchPort,
        jwtUseCase
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
        val localId = "localId"
        val password = "password"
        val id = "id"
        val member = LocalMember(id,"nickname", Lists.emptyList(), localId, password)

        /**
         * when
         */
        every { memberSearchPort.findLocalMemberBylocalId(localId) } returns member
        every { jwtUseCase.generateAccessToken(member) } returns JWT("accessToken!")
        every { jwtUseCase.generateRefreshToken(member) } returns JWT("refreshToken")

        /**
         * then
         */
        assertEquals(memberSignInService.signInLocalMember(localId, password).member.id, id)
    }
}
