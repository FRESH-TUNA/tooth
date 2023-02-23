package com.freshtuna.openshop.auth

import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.jwt.JWT

import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.member.LocalMember
import io.mockk.InternalPlatformDsl.toStr

import io.mockk.every

import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID


@ExtendWith(MockKExtension::class)
class LocalSignUpJWTServiceTest {

    private val localSignUpPort: LocalSignUpPort = mockk()

    private val memberSearchPort: MemberSearchPort = mockk()

    private val jwtUseCase: JWTUseCase = mockk()

    private val memberSignUpService = LocalSignUpJWTService(
        localSignUpPort,
        memberSearchPort,
        jwtUseCase
    )

    @Test
    @DisplayName("이미 가입된 아이디로는 로컬 회원 가입이 불가하다.")
    fun signupFailWhenSameLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */
        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        val member = LocalMember(null, nickname, roles, localId, password)

        /**
         * when
         */
        every { localSignUpPort.signUp(any()) } returns member
        every { memberSearchPort.existsLocalMemberBylocalId(localId) } returns true

        /**
         * then
         * 로컬멤버 생성 테스트
         */
        assertThrows<OpenException> { memberSignUpService.signUp(member) }
    }

    @Test
    @DisplayName("새로운 아이디라면 로컬 회원 가입이 가능하다.")
    fun signupSuccessWhenNewLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        val member = LocalMember(null, nickname, roles, localId, password)

        /**
         * when
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        val newId = UUID.randomUUID().toStr()
        val newMember = LocalMember(newId, nickname, roles, localId, password)

        every { localSignUpPort.signUp(any<LocalMember>()) } returns newMember
        every { memberSearchPort.existsLocalMemberBylocalId(localId) } returns false
        every { jwtUseCase.generateAccessToken(newMember) } returns JWT("accessToken!")
        every { jwtUseCase.generateRefreshToken(newMember) } returns JWT("refreshToken")

        /**
         * then
         * 로컬멤버 생성 테스트
         */
        Assertions.assertEquals(memberSignUpService.signUp(member).member.id, newId)
    }
}
