package com.freshtuna.tooth.token

import com.freshtuna.tooth.*
import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.Member
import com.freshtuna.tooth.member.OAuthMember
import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.member.constant.Provider
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase

import io.jsonwebtoken.security.Keys
import io.mockk.mockk
import org.assertj.core.util.Lists

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class TokenManagerTest {
    private val jwtUtil: ManageTokenUseCase = createJWTUtilJJWTImpl()

    @Test
    @DisplayName("로컬 멤버 액세스 토큰 생성 테스트")
    fun generateAccessTokenForLocalMember() {

        /**
         * given
         * 토큰을 생성하고 싶은 멤버, 토큰을 생성할 테스트 객체
         */
        val localMember = createLocalMember()

        /**
         * when
         * 토큰 생성
         */
        jwtUtil.generate(localMember)
    }

    @Test
    @DisplayName("OAuth 소셜 멤버 액세스 토큰 생성 테스트")
    fun generateAccessTokenForOAuthMember() {
        /**
         * given
         * 토큰을 생성하고 싶은 멤버, 토큰을 생성할 테스트 객체
         */
        val oAuthMember = createOAuthMember()

        /**
         * when
         * 토큰 생성
         */
        jwtUtil.generate(oAuthMember)
    }

    /**
     * helpers
     */
    private fun createJWTUtilJJWTImpl(): ManageTokenUseCase {
        val key = "this is key this is key this is key this is key this is key this is key this is key"
        val tokenExpiredMileSeconds = 5000L

        val roleKey = "ROLES"
        val prefix = "Bearer"

        return TokenManager(
            Keys.hmacShaKeyFor(key.toByteArray()),
            tokenExpiredMileSeconds,
            roleKey,
            prefix,
            mockk())
    }

    private fun createLocalMember(): Member {
        // 고유 식별자
        val id = ID("식별자")
        val publicID = ID("공개식별자")

        // 권한
        val roles: List<Role> = Lists.emptyList()

        /**
         * given
         * 로컬 사용자에 필요한 정보
         */
        // 로그인 ID
        val localId = ID("freshtuna@kakao.com")
        val password = Password("encryptedPassword")

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        return LocalMember(id, publicID, roles, localId, password)
    }

    private fun createOAuthMember(): Member {
        // 고유 식별자
        val id = ID("식별자")

        val publicId = ID("공개ID")

        // 권한
        val roles: List<Role> = Lists.emptyList()

        // OAuth 서비스 프로바이더
        val provider = Provider.GOOGLE

        // oauth 프로바이더가 제공하는 id (sub)
        val oauthId = "oauthId"

        return OAuthMember(id, publicId, roles, provider, oauthId)
    }
}
