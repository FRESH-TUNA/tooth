package com.freshtuna.openshop.member.adapter

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.EncryptedPassword
import com.freshtuna.openshop.member.entity.MariaDBLocalMember
import com.freshtuna.openshop.member.id.LocalId
import com.freshtuna.openshop.member.id.PublicId
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
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
        val publicId = UUID.randomUUID()
        val curPassword = EncryptedPassword("curPassword")
        val newPassword = EncryptedPassword("newPassword")
        val member = LocalMember(PublicId( publicId.toStr()), emptyList(), LocalId("localId"), curPassword)
        val dbMember = MariaDBLocalMember("localId", "password", "nickname")

        /**
         * when
         */
        every {
            localMemberRepository.findByPublicId(publicId)
        } returns Optional.of(dbMember)

        updateAdapter.changePassword(member, newPassword)

        /**
         * then
         */
        Assertions.assertEquals(dbMember.password, newPassword.passwordString)
    }
}
