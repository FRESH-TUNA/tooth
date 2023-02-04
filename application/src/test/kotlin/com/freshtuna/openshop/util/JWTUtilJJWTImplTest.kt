package com.freshtuna.openshop.util

import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.Member
import com.freshtuna.openshop.Role
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.security.Key


class JWTUtilJJWTImplTest {

    @Test
    @DisplayName("구현체 생성 테스트")
    fun createJWTUtilTest() {
        /**
         * given
         * key, 액세스 토큰 유효기간(ms), 리프레시 토큰 유효기간(ms)
         */
        val key = "this is key this is key this is key this is key this is key this is key this is key"
        val accessTokenExpiredMileSeconds = 5000L
        val refreshTokenExpiredMileSeconds = 1000L

        /**
         * when
         */
        val jwtUtil = JWTUtilJJWTImpl(
            key,
            accessTokenExpiredMileSeconds,
            refreshTokenExpiredMileSeconds)

        /**
         * then
         */
        assertEquals(true, jwtUtil is JWTUtil)
    }
}