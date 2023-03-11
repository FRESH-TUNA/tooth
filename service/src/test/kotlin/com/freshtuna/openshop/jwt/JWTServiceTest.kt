package com.freshtuna.openshop.jwt

import com.freshtuna.openshop.*
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Member
import com.freshtuna.openshop.member.OAuthMember
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.constant.Provider
import com.freshtuna.openshop.jwt.incoming.JWTUseCase

import io.jsonwebtoken.security.Keys
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class JWTServiceTest {

    @Test
    @DisplayName("구현체 생성 테스트")
    fun createJWTUtilTest() {

        /**
         * then
         */
        assertEquals(true, createJWTUtilJJWTImpl() is JWTUseCase)
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
         */
        assertEquals(true, token is JWT)
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
        assertEquals(true, token is JWT)
    }

    @Test
    @DisplayName("로컬, oauth 계정이 발행한 token에서 id 추출 테스트")
    fun idOfToken() {
        /**
         * given
         * 토큰을 생성하고 싶은 멤버, 토큰을 생성할 테스트 객체
         */
        val localId = "thisislocalId"
        val localMember = createLocalMember(localId)

        val oauthId = "thisisOAuthId"
        val oAuthMember = createOAuthMember(oauthId)

        val jwtUtil = createJWTUtilJJWTImpl()

        /**
         * when
         * 토큰 생성
         */
        val localToken = jwtUtil.generateAccessToken(localMember)
        val oauthToken = jwtUtil.generateAccessToken(oAuthMember)

        /**
         * then
         */
        assertEquals(localId, jwtUtil.publicIdOfAccess(localToken))
        assertEquals(oauthId, jwtUtil.publicIdOfAccess(oauthToken))
    }

    @Test
    @DisplayName("리프레시 토큰을 통한 재발행 테스트")
    fun refreshTest() {
        /**
         * given
         * 토큰을 생성하고 싶은 멤버, 토큰을 생성할 테스트 객체, 리프레시 토큰
         */
        val jwtUtil = createJWTUtilJJWTImpl()

        val localId = "thisislocalId"
        val localMember = createLocalMember(localId)
        val refreshToken = jwtUtil.generateAccessToken(localMember)

        /**
         * when
         * 새로운 엑세스 토큰 생성
         */
        val accessToken = jwtUtil.refresh(refreshToken)

        /**
         * then
         */
        jwtUtil.checkAccessToken(accessToken)

        assertEquals(jwtUtil.publicIdOfAccess(accessToken), jwtUtil.publicIdOfRefresh(refreshToken))
        assertEquals(
            jwtUtil.roleOfAccess(accessToken),
            jwtUtil.roleOfRefresh(refreshToken)
        )
    }


    /**
     * helpers
     */
    private fun createJWTUtilJJWTImpl(): JWTUseCase {
        val key = "this is key this is key this is key this is key this is key this is key this is key"
        val refreshKey = "this is key this is key this is key this is key this is key this is key this is key"
        val accessTokenExpiredMileSeconds = 5000L
        val refreshTokenExpiredMileSeconds = 1000L
        val roleKey = "ROLES"
        val prefix = "Bearer"

        return JWTService(
            Keys.hmacShaKeyFor(key.toByteArray()),
            Keys.hmacShaKeyFor(refreshKey.toByteArray()),
            accessTokenExpiredMileSeconds,
            refreshTokenExpiredMileSeconds,
            roleKey,
            prefix)
    }

    private fun createLocalMember(): Member {
        // 고유 식별자
        val id = "식별자"

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

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        return LocalMember(id, nickname, roles, localId)
    }

    private fun createLocalMember(id: String): Member {

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

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        return LocalMember(id, nickname, roles, localId)
    }

    private fun createOAuthMember(): Member {
        // 고유 식별자
        val id = "식별자"

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // OAuth 서비스 프로바이더
        val provider = Provider.GOOGLE

        // oauth 프로바이더가 제공하는 id (sub)
        val oauthId = "oauthId"

        return OAuthMember(id, nickname, roles, provider, oauthId)
    }

    private fun createOAuthMember(id: String): Member {

        // 부가정보
        val nickname = "신선한참치"

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // OAuth 서비스 프로바이더
        val provider = Provider.GOOGLE

        // oauth 프로바이더가 제공하는 id (sub)
        val oauthId = "oauthId"

        return OAuthMember(id, nickname, roles, provider, oauthId)
    }
}
