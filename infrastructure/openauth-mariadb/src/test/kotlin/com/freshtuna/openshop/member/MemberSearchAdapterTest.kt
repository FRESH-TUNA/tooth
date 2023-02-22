package com.freshtuna.openshop.member

import com.freshtuna.openshop.exception.OpenException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.collections.ArrayList

class MemberSearchAdapterTest {

    private val memberRepository: MariaDBMemberRepository = mockk()
    private val localMemberRepository: MariaDBLocalMemberRepository = mockk()
    private val memberSearchAdapter = MemberSearchAdapter(memberRepository, localMemberRepository)

    @Test
    @DisplayName("유효한 로컬 id 존재 성공 테스트")
    fun existsLocalMemberBylocalIdSuccess() {
        /**
         * given
         */
        val localId = "existed"

        every { localMemberRepository.existsByLocalId(localId) } returns true

        /**
         * when, then
         */
        assertEquals(memberSearchAdapter.existsLocalMemberBylocalId(localId), true)
    }

    @Test
    @DisplayName("유효하지 않은 로컬 id 존재 실패 테스트")
    fun existsLocalMemberBylocalIdFailure() {
        /**
         * given
         */
        val localId = "existed"

        /**
         * when
         */
        every { localMemberRepository.existsByLocalId(localId) } returns false

        /**
         * then
         */
        assertEquals(memberSearchAdapter.existsLocalMemberBylocalId(localId), false)
    }

    @Test
    @DisplayName("로컬 id를 가진 유저 반환 성공 테스트")
    fun findLocalMemberBylocalIdSuccess() {
        /**
         * given
         */
        val localId = "existed"
        val mariaDBLocalMember = MariaDBLocalMember(
            localId, "password", 0L, UUID.randomUUID(), "nickname", ArrayList())

        /**
         * when
         * localId를 가진 유저가 있다면
         */
        every { localMemberRepository.findByLocalId(localId) } returns Optional.of(mariaDBLocalMember)
        val localMember = memberSearchAdapter.findLocalMemberBylocalId(localId)

        /**
         * then
         */
        assertEquals(localMember.localId, localId)
    }

    @Test
    @DisplayName("로컬 id를 가진 유저 반환 실패 테스트")
    fun findLocalMemberBylocalIdFail() {
        /**
         * given
         */
        val localId = "existed"

        /**
         * when
         * 만약 localId를 가진 로컬 유저가 없다면
         */
        every { localMemberRepository.findByLocalId(localId) } returns Optional.empty()

        /**
         * then
         */
        assertThrows<OpenException> { memberSearchAdapter.findLocalMemberBylocalId(localId) }
    }
}
