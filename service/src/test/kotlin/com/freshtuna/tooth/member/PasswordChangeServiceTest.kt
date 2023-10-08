package com.freshtuna.tooth.member

import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.id.ID

import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import com.freshtuna.tooth.member.outgoing.LocalMemberUpdatePort
import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordChangeServiceTest {

    private val localMemberUpdatePort: LocalMemberUpdatePort = mockk()

    private val localMemberSearchPort: LocalMemberSearchPort = mockk()

    private val securedPasswordUseCase: SecuredPasswordUseCase = mockk()

    private val passwordChangeService = LocalPasswordChangeComposite(
        localMemberUpdatePort,
        localMemberSearchPort,
        securedPasswordUseCase
    )

    @Test
    @DisplayName("패스워드 변경 성공 테스트")
    fun changePassword() {
        /**
         * given
         */
        val localId = ID("localId")
        val curPassword = Password("password")
        val newPassword = Password("1aB!1aB!1aB!1aB!1aB!")

        val curEncryptedPassword = Password("password")

        val id = ID("id")
        val publicID = ID("publicID")
        val member = LocalMember(id, publicID, Lists.emptyList(), localId, curEncryptedPassword)

        /**
         * when
         */
        every { securedPasswordUseCase.matched(curPassword, curEncryptedPassword) } returns true
        every { securedPasswordUseCase.encrypt(curPassword) } returns Unit
        every { securedPasswordUseCase.encrypt(newPassword) } returns Unit

        every { localMemberSearchPort.findBy(id) } returns member
        every { localMemberUpdatePort.changePassword(member, newPassword) } returns Unit



        /**
         * then
         */
        assertDoesNotThrow { passwordChangeService.change(id, curPassword, newPassword) }
    }

    @Test
    @DisplayName("패스워드 룰 미준수 실패 테스트")
    fun doNotBreakPasswordRule() {
        /**
         * given
         */
        val localId = ID("localId")
        val curPassword = Password("password")
        val newPassword = Password("hmm")

        val curEncryptedPassword = Password("password")
        val newEncryptedPassword = Password("password")

        val id = ID("id")
        val publicID = ID("publicID")
        val member = LocalMember(id, publicID, Lists.emptyList(), localId, curEncryptedPassword)

        /**
         * when
         */
        every { securedPasswordUseCase.matched(curPassword, curEncryptedPassword) } returns true
        every { securedPasswordUseCase.encrypt(curPassword) } returns Unit
        every { securedPasswordUseCase.encrypt(newPassword) } returns Unit

        every { localMemberSearchPort.findBy(id) } returns member
        every { localMemberUpdatePort.changePassword(member, newEncryptedPassword) } returns Unit

        /**
         * then
         */
        assertThrows<ToothException> { passwordChangeService.change(id, curPassword, newPassword) }
    }
}
