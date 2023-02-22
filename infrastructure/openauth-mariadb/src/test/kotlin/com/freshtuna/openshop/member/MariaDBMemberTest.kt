package com.freshtuna.openshop.member

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.collections.ArrayList

class MariaDBMemberTest {

    @Test
    @DisplayName("멤버 생성 테스트")
    fun createTest() {
        /**
         * given
         */
        val id = 1L
        val publicId = UUID.randomUUID()
        val nickname = "nickname"
        val roles = ArrayList<MariaDBRole>()

        /**
         * when, then
         */
        MariaDBMember(id, publicId, nickname, roles)
    }
}
