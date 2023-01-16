package com.freshtuna.openshop

import org.assertj.core.util.Lists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LocalMemberTest {
    @Test
    @DisplayName("로컬 사용자에 필요한 정보 테스트")
    fun localMemberTest() {

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
        val member = LocalMember(id, name, nickname, roles, localId, password)

        /**
         * then
         * 필요한 정보들이 잘 설정되어있는지 확인
         */
        assertEquals(member.id, id)
        assertEquals(member.name, name)
        assertEquals(member.nickname, nickname)
        assertEquals(member.roles === roles, true)

        assertEquals(member.localId, localId)
        assertEquals(member.password, password)
    }
}