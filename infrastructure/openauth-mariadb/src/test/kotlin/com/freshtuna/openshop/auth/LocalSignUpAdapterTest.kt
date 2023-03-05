package com.freshtuna.openshop.auth

import com.freshtuna.openshop.auth.outgoing.LocalSignUpPort
import com.freshtuna.openshop.member.*
import com.freshtuna.openshop.auth.adapter.LocalSignUpAdapter
import com.freshtuna.openshop.member.entity.MariaDBLocalMember
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*
import kotlin.collections.ArrayList

class LocalSignUpAdapterTest {

    private val memberRepository: MariaDBLocalMemberRepository = mockk()
    private val localSignUpPort: LocalSignUpPort = LocalSignUpAdapter(memberRepository)

    @Test
    @DisplayName("로컬 회원가입 성공 테스트")
    fun signUpSuccess() {
        /**
         * given
         */
        val member = LocalMember(null, "nickname", ArrayList(), "wishToId")
        val password = SecuredPassword("password")

        /**
         * when
         */
        val savedDbMember = MariaDBLocalMember("wishToId", "password", "nickname")

        every { memberRepository.save(any()) } returns savedDbMember

        /**
         * then
         */
        assertEquals(localSignUpPort.signUp(member, password).id, savedDbMember.publicId.toStr())
    }
}
