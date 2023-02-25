package com.freshtuna.openshop.member

import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.member.outgoing.LocalMemberUpdatePort
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PasswordChangeServiceTest {

    private val localMemberUpdatePort: LocalMemberUpdatePort = mockk()

    private val passwordChangeService = PasswordChangeService(localMemberUpdatePort)

    @Test
    @DisplayName("패스워드 변경 성공 테스트")
    fun changePassword() {
        /**
         * given
         */
        val localId = "localId"
        val password = "password"
        val id = "id"
        val member = LocalMember(id,"nickname", Lists.emptyList(), localId, password)

        /**
         * when, then
         */
        every { localMemberUpdatePort.changePassword(member) } returns Unit
        assertDoesNotThrow { passwordChangeService.changePassword(member, "1aB!1aB!1aB!1aB!1aB!") }
    }

    @Test
    @DisplayName("패스워드 룰 미준수 실패 테스트")
    fun doNotBreakPasswordRule() {
        /**
         * given
         */
        val localId = "localId"
        val password = "password"
        val id = "id"
        val member = LocalMember(id,"nickname", Lists.emptyList(), localId, password)

        /**
         * when, then
         */
        every { localMemberUpdatePort.changePassword(member) } returns Unit
        assertThrows<OpenException> { passwordChangeService.changePassword(member, "hmm") }
    }
}