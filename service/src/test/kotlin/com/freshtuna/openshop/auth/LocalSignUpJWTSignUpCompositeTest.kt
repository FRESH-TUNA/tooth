package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.command.LocalSignUpCommand
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.jwt.JWT

import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.EncryptedPassword
import com.freshtuna.openshop.id.LocalId
import com.freshtuna.openshop.id.PublicId
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import io.mockk.InternalPlatformDsl.toStr

import io.mockk.every

import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.*
import java.util.UUID

class LocalSignUpJWTSignUpCompositeTest {

    private val localSignUpPort: LocalSignUpPort = mockk()

    private val memberSearchPort: MemberSearchPort = mockk()

    private val jwtUseCase: JWTUseCase = mockk()

    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val memberSignUpService = LocalSignUpComposite(
        localSignUpPort,
        memberSearchPort,
        jwtUseCase,
        securedPasswordUseCase
    )

    @Test
    @DisplayName("이미 가입된 아이디로는 로컬 회원 가입이 불가하다.")
    fun signupFailWhenSameLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = LocalId("freshtuna@kakao.com")

        // 패스워드
        val password = Password("패스워드")

        val member = LocalMember(
            PublicId("hohohoho"), roles, localId, EncryptedPassword("encrypted"))

        /**
         * when
         */
        every { localSignUpPort.signUp(any(), any()) } returns member
        every { memberSearchPort.existsLocalMember(localId) } returns true

        /**
         * then
         * 로컬멤버 생성 테스트
         */
        assertThrows<OpenException> { memberSignUpService.signUp(LocalSignUpCommand(localId, password, password)) }
    }

    @Test
    @DisplayName("새로운 아이디라면 로컬 회원 가입이 가능하다.")
    fun signupSuccessWhenNewLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = LocalId("freshtuna@kakao.com")

        // 패스워드
        val password = Password("1aB!1aB2")

        /**
         * when
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        val newId = PublicId(UUID.randomUUID().toStr())
        val newMember = LocalMember(newId, roles, localId, EncryptedPassword("encrypted"))

        every { localSignUpPort.signUp(any(), any()) } returns newMember
        every { memberSearchPort.existsLocalMember(localId) } returns false

        every { securedPasswordUseCase.generate(any()) } returns EncryptedPassword("thisISsecure!!")
        every { jwtUseCase.generateAccessToken(newMember) } returns JWT.accessOf("accessToken!")
        every { jwtUseCase.generateRefreshToken(newMember) } returns JWT.refreshOf("refreshToken")

        /**
         * then
         * 로컬멤버 생성 테스트
         */
        Assertions.assertEquals(
            memberSignUpService.signUp(LocalSignUpCommand(localId, password, password)).member.publicId, newId)
    }
}
