package com.freshtuna.openshop.member

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.ArrayList

class MariaDBLocalMemberTest {

    @Test
    @DisplayName("로컬 멤버 생성 테스트")
    fun createTest() {

        /**
         * given
         */
        val localId = "thisIsLocalId"
        val password = "password"

        val id = 1L
        val publicId = UUID.randomUUID()
        val nickname = "nickname"
        val roles = ArrayList<MariaDBRole>()

        MariaDBLocalMember(localId, password, id, publicId, nickname, roles)
    }
}
