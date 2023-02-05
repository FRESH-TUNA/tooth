package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.member.out.MemberSearchPort
import com.freshtuna.openshop.util.JWTUtil

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class MemberSignInJWTServiceTest {

    private val memberSearchPort: MemberSearchPort = mockk()
    private val jwtUtil: JWTUtil = mockk()

    private val memberSignInService = MemberSignInJWTService(
        memberSearchPort,
        jwtUtil
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
        val member = LocalMember(id,"name","nickname", Lists.emptyList(), localId, password)

        every { memberSearchPort.findLocalMemberBylocalId(localId) } returns member
        every { jwtUtil.generateAccessToken(member) } returns "accessToken!"
        every { jwtUtil.generateRefreshToken(member) } returns "refreshToken"

        /**
         * when
         */
        val signInedMember = memberSignInService.signInLocalMember(localId, password)

        /**
         * then
         */
        assertEquals(signInedMember.member.id, id)
    }
}
