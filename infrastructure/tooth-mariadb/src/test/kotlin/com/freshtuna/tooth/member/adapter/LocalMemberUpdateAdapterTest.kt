package com.freshtuna.tooth.member.adapter

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.Password

import com.freshtuna.tooth.member.entity.MariaDBLocalMember
import com.freshtuna.tooth.member.repository.MariaDBLocalMemberRepository
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class LocalMemberUpdateAdapterTest {

    private val localMemberRepository: MariaDBLocalMemberRepository = mockk()
    private val updateAdapter = LocalMemberUpdateAdapter(localMemberRepository)

    @Test
    @DisplayName("패스워드 변경 테스트")
    fun changePassword() {
        /**
         * given
         */
        val id = ID(1)
        val publicId = UUID.randomUUID()
        val curPassword = Password("curPassword")
        val newPassword = Password("newPassword")
        val member = LocalMember(id, ID( publicId.toStr()), emptyList(), ID("localId"), curPassword)
        val dbMember = MariaDBLocalMember("localId", "password", "nickname")

        /**
         * when
         */
        every {
            localMemberRepository.findById(any())
        } returns Optional.of(dbMember)

        updateAdapter.changePassword(member, newPassword)

        /**
         * then
         */
        Assertions.assertEquals(dbMember.password, newPassword.passwordString)
    }
}
