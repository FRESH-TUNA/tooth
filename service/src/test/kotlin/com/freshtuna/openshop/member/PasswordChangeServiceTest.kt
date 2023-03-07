package com.freshtuna.openshop.member

import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.member.incoming.SecuredPasswordUseCase
import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import com.freshtuna.openshop.member.outgoing.MemberSearchPort
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordChangeServiceTest {

    private val localMemberUpdatePort: LocalMemberUpdatePort = mockk()

    private val memberSearchPort: MemberSearchPort = mockk()

    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val passwordChangeService = PasswordChangeService(
        localMemberUpdatePort,
        memberSearchPort,
        securedPasswordUseCase
    )

    @Test
    @DisplayName("패스워드 변경 성공 테스트")
    fun changePassword() {
        /**
         * given
         */
        val localId = "localId"
        val curPassword = Password("password")
        val newPassword = Password("1aB!1aB!1aB!1aB!1aB!")

        val curSecuredPassword = SecuredPassword("password")
        val newSecuredPassword = SecuredPassword("password")

        val id = "id"
        val member = LocalMember(id,"nickname", Lists.emptyList(), localId)

        /**
         * when
         */
        every { securedPasswordUseCase.matched(curPassword, curSecuredPassword) } returns true
        every { securedPasswordUseCase.generate(curPassword) } returns curSecuredPassword
        every { securedPasswordUseCase.generate(newPassword) } returns newSecuredPassword

        every { memberSearchPort.findLocalMember(id) } returns member
        every { memberSearchPort.findSavedPasswordByLocalMember(member) } returns curSecuredPassword
        every { localMemberUpdatePort.changePassword(member, newSecuredPassword) } returns Unit



        /**
         * then
         */
        assertDoesNotThrow { passwordChangeService.changePassword(id, curPassword, newPassword) }
    }

    @Test
    @DisplayName("패스워드 룰 미준수 실패 테스트")
    fun doNotBreakPasswordRule() {
        /**
         * given
         */
        val localId = "localId"
        val curPassword = Password("password")
        val newPassword = Password("hmm")

        val curSecuredPassword = SecuredPassword("password")
        val newSecuredPassword = SecuredPassword("password")

        val id = "id"
        val member = LocalMember(id,"nickname", Lists.emptyList(), localId)

        /**
         * when
         */
        every { securedPasswordUseCase.matched(curPassword, curSecuredPassword) } returns true
        every { securedPasswordUseCase.generate(curPassword) } returns curSecuredPassword
        every { securedPasswordUseCase.generate(newPassword) } returns newSecuredPassword

        every { memberSearchPort.findLocalMember(id) } returns member
        every { memberSearchPort.findSavedPasswordByLocalMember(member) } returns curSecuredPassword
        every { localMemberUpdatePort.changePassword(member, newSecuredPassword) } returns Unit

        /**
         * then
         */
        assertThrows<OpenException> { passwordChangeService.changePassword(id, curPassword, newPassword) }
    }
}