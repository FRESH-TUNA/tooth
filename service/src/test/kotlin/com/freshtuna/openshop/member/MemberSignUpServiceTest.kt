package com.freshtuna.openshop.member

import com.freshtuna.openshop.auth.LocalSignUpService
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.member.outgoing.MemberUpdatePort
import io.mockk.every

import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
class MemberSignUpServiceTest {

    private val localSignUpPort: LocalSignUpPort = mockk()

    private val memberSearchPort: MemberSearchPort = mockk()

    private val memberUpdatePort: MemberUpdatePort = mockk()

    private val memberSignUpService = LocalSignUpService(
        localSignUpPort,
        memberSearchPort
    )

    @Test
    @DisplayName("이미 가입된 아이디로는 로컬 회원 가입이 불가하다.")
    fun signupFailWhenSameLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */
        // 고유 식별자
        val id = "식별자"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        val member = LocalMember(id, nickname, roles, localId, password)

        /**
         * given
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        every { localSignUpPort.signUp(any()) } returns member
        every { memberSearchPort.existsLocalMemberBylocalId(localId) } returns true


        /**
         * when, then
         * 로컬멤버 생성 테스트
         */
        assertThrows<RuntimeException> { memberSignUpService.signUpLocalMember(member) }
    }

    @Test
    @DisplayName("새로운 아이디라면 로컬 회원 가입이 가능하다.")
    fun signupSuccessWhenNewLocalId() {
        /**
         * given
         * 로컬멤버 생성하기
         */
        // 고유 식별자
        val id = "식별자"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        val member = LocalMember(id, nickname, roles, localId, password)

        /**
         * given
         * 테스트에 사용할 서비스 객체, 포트 객체
         */
        every { localSignUpPort.signUp(any<LocalMember>()) } returns member
        every { memberSearchPort.existsLocalMemberBylocalId(localId) } returns false

        /**
         * when, then
         * 로컬멤버 생성 테스트
         */
        assertDoesNotThrow { memberSignUpService.signUpLocalMember(member) }
    }
}
