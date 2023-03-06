package com.freshtuna.openshop.member.entity

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
        val nickname = "nickname"

        /**
         * when, then
         */
        MariaDBMember(nickname)
    }
}
