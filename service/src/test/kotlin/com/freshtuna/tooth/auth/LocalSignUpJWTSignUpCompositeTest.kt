package com.freshtuna.tooth.auth

import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort
import com.freshtuna.tooth.member.outgoing.NewLocalMemberPort
import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.id.incoming.LocalIdValidateUseCase
import com.freshtuna.tooth.token.AuthToken

import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.Password

import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import io.mockk.InternalPlatformDsl.toStr

import io.mockk.every

import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.*
import java.util.UUID

class LocalSignUpJWTSignUpCompositeTest {

    private val newLocalMemberPort: NewLocalMemberPort = mockk()

    private val localMemberSearchPort: LocalMemberSearchPort = mockk()

    private val tokenManager: ManageTokenUseCase = mockk()

    private val refreshTokenManager: ManageTokenUseCase = mockk()

    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val localIdValidateUseCase: LocalIdValidateUseCase = mockk()

    private val memberSignUpService = SignUpComposite(
        newLocalMemberPort,
        localMemberSearchPort,
        tokenManager,
        refreshTokenManager,
        securedPasswordUseCase,
        localIdValidateUseCase
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
        val localId = ID("freshtuna@kakao.com")

        // 패스워드
        val password = Password("패스워드")

        val member = LocalMember(
            ID("hohohoho"), ID("hohohoho"), roles, localId, Password("encrypted"))

        /**
         * when
         */
        every { newLocalMemberPort.new(any()) } returns member
        every { localMemberSearchPort.existsByLocalId(localId) } returns true
        every { localIdValidateUseCase.validate(any()) } returns Unit

        /**
         * then
         * 로컬멤버 생성 테스트
         */
        assertThrows<ToothException> { memberSignUpService.signUp(SignUpCommand(localId, password, password)) }
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
        val localId = ID("freshtuna@kakao.com")

        // 패스워드
        val password = Password("1aB!1aB2")

        /**
         * when
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        val accessToken = AuthToken.of("accessToken!")
        val refreshToken = AuthToken.of("refreshToken!")

        val newId = ID(UUID.randomUUID().toStr())
        val newMember = LocalMember(newId, newId, roles, localId, Password("encrypted"))

        every { newLocalMemberPort.new(any()) } returns newMember
        every { localMemberSearchPort.existsByLocalId(localId) } returns false

        every { securedPasswordUseCase.encrypt(any()) } returns Unit

        every { tokenManager.generate(newMember) } returns accessToken
        every { refreshTokenManager.generate(newMember) } returns refreshToken
        every { localIdValidateUseCase.validate(any()) } returns Unit
        /**
         * then
         * 로컬멤버 생성 테스트
         */
        val result = memberSignUpService.signUp(SignUpCommand(localId, password, password))
        Assertions.assertEquals(result.access, accessToken)
        Assertions.assertEquals(result.refresh, refreshToken)
    }
}
