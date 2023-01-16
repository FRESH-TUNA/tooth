package com.freshtuna.openshop

import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OAuthMemberTest {
    @Test
    @DisplayName("로컬 사용자에 필요한 정보 테스트")
    fun oAuthMemberTest() {

        /**
         * given
         * 사용자에 필요한 정보
         */
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
         * OAuth 사용자에 필요한 정보
         */
        // OAuth 서비스 프로바이더
        val provider = OAuthProvider.GOOGLE

        // oauth 프로바이더가 제공하는 id (sub)
        val oauthId = "oauthId"

        /**
         * when
         * 주어진 정보들을 통해 로컬 멤버 생성하기
         */
        val member = OAuthMember(id, name, nickname, roles, provider, oauthId)

        /**
         * then
         * 필요한 정보들이 잘 설정되어있는지 확인
         */
        Assertions.assertEquals(member.id, id)
        Assertions.assertEquals(member.name, name)
        Assertions.assertEquals(member.nickname, nickname)
        Assertions.assertEquals(member.roles === roles, true)

        Assertions.assertEquals(member.provider, OAuthProvider.GOOGLE)
        Assertions.assertEquals(member.oauthId, oauthId)
    }
}
