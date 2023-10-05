package com.freshtuna.tooth.auth

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.outgoing.NewLocalMemberPort
import com.freshtuna.tooth.member.*
import com.freshtuna.tooth.member.adapter.NewLocalMemberAdapter
import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.member.entity.MariaDBLocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class LocalSignUpAdapterTest {

    private val memberRepository: MariaDBLocalMemberRepository = mockk()
    private val newLocalMemberPort: NewLocalMemberPort = NewLocalMemberAdapter(memberRepository)

    @Test
    @DisplayName("로컬 회원가입 성공 테스트")
    fun signUpSuccess() {
        /**
         * given
         */
        val password = Password("myPassword")
        val encryptedPassword = Password("password")
        val command = SignUpCommand(ID("wishToId"), password, password)
        /**
         * when
         */
        val savedDbMember = MariaDBLocalMember("wishToId", "password", "nickname")

        every { memberRepository.save(any()) } returns savedDbMember

        /**
         * then
         */
        assertEquals(
            newLocalMemberPort.new(command).localID.toString(), savedDbMember.localId
        )
    }
}
