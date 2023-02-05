package com.freshtuna.openshop.util

import com.freshtuna.openshop.*
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

    @Test
    @DisplayName("로컬 멤버 액세스 토큰 생성 테스트")
    fun generateAccessTokenForLocalMember() {
        /**
         * given
         * 토큰을 생성하고 싶은 멤버, 토큰을 생성할 테스트 객체
         */
        val localMember = createLocalMember()
        val jwtUtil = createJWTUtilJJWTImpl()

        /**
         * when
         * 토큰 생성
         */
        val token = jwtUtil.generateAccessToken(localMember)

        /**
         * then
         * 토큰은 문자열타입으로 반환되는지 체크
         */
        assertEquals(true, token is String)
    }

    @Test
    @DisplayName("OAuth 소셜 멤버 액세스 토큰 생성 테스트")
    fun generateAccessTokenForOAuthMember() {
        /**
         * given
         * 토큰을 생성하고 싶은 멤버, 토큰을 생성할 테스트 객체
         */
        val oAuthMember = createOAuthMember()
        val jwtUtil = createJWTUtilJJWTImpl()

        /**
         * when
         * 토큰 생성
         */
        val token = jwtUtil.generateAccessToken(oAuthMember)

        /**
         * then
         * 토큰은 문자열타입으로 반환되는지 체크
         */
        assertEquals(true, token is String)
    }


    private fun createJWTUtilJJWTImpl(): JWTUtil {
        val key = "this is key this is key this is key this is key this is key this is key this is key"
        val accessTokenExpiredMileSeconds = 5000L
        val refreshTokenExpiredMileSeconds = 1000L

        return JWTUtilJJWTImpl(key, accessTokenExpiredMileSeconds, refreshTokenExpiredMileSeconds)
    }

    private fun createLocalMember(): Member {
        // 고유 식별자
        val id = "식별자"

        // 개인정보 (실명)
        val name = "김동원"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        /**
         * given
         * 로컬 사용자에 필요한 정보
         */
        // 로그인 ID
        val localId = "freshtuna@kakao.com"

        // 패스워드
        val password = "패스워드"

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        return LocalMember(id, name, nickname, roles, localId, password)
    }

    private fun createOAuthMember(): Member {
        // 고유 식별자
        val id = "식별자"

        // 개인정보 (실명)
        val name = "김동원"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // OAuth 서비스 프로바이더
        val provider = OAuthProvider.GOOGLE

        // oauth 프로바이더가 제공하는 id (sub)
        val oauthId = "oauthId"

        return OAuthMember(id, name, nickname, roles, provider, oauthId)
    }
}