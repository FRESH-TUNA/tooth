package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role
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
        val member = LocalMember(id, nickname, roles, localId, password)

        /**
         * then
         * 필요한 정보들이 잘 설정되어있는지 확인
         */
        assertEquals(member.id, id)
        assertEquals(member.nickname, nickname)
        assertEquals(member.roles === roles, true)

        assertEquals(member.localId, localId)
        assertEquals(member.password, password)
    }

    @Test
    @DisplayName("패스워드 규칙 검증")
    fun checkPasswordRuleTest() {
        /**
         * given
         * 사용자에 필요한 정보
         */
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
         * given
         * 테스트에 필요한 규칙에 어긋나는 패스워드를 사용한 로컬사용자들을 모두 생성
         */
        val member1 = LocalMember(id, nickname, roles, localId, "1aB!1aB")
        val member2 = LocalMember(id, nickname, roles, localId, "1aB!1aB!1aB!1aB!1aB!1")
        val member3 = LocalMember(id, nickname, roles, localId, "aaB!aaB!")
        val member4 = LocalMember(id, nickname, roles, localId, "1ab!1ab!")
        val member5 = LocalMember(id, nickname, roles, localId, "1AB!1AB!")
        val member6 = LocalMember(id, nickname, roles, localId, "1AB11AB1")
        val member7 = LocalMember(id, nickname, roles, localId, "1AB!1AB ")

        /**
         * when and then
         * 패스워드는 8자이상 20자 이하여야 한다.
         */
        assertEquals(member1.checkPasswordRule(), false)
        assertEquals(member2.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 숫자가 필요하다.
         */
        assertEquals(member3.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 대문자가 필요하다.
         */
        assertEquals(member4.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 소문자가 필요하다.
         */
        assertEquals(member5.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드는 적어도 하나의 특수문자가 있어야 한다.
         */
        assertEquals(member6.checkPasswordRule(), false)

        /**
         * when and then
         * 패스워드에는 공백문자가 포함되면 안된다.
         */
        assertEquals(member7.checkPasswordRule(), false)

        /**
         * given, when, then
         * 테스트에 필요한 규칙이 준수된 패스워드를 사용한 로컬사용자들을 모두 생성
         */
        val normalMember1 = LocalMember(id, nickname, roles, localId, "1aB!1aB!1aB!1aB!1aB!")
        val normalMember2 = LocalMember(id, nickname, roles, localId, "1aB!1aB!")

        assertEquals(normalMember1.checkPasswordRule(), true)
        assertEquals(normalMember2.checkPasswordRule(), true)
    }

    @Test
    @DisplayName("패스워드 체크 실패 테스트")
    fun checkPasswordFail() {
        /**
         * given
         * 테스트에 필요한 멤버
         */
        val member1 = LocalMember("id", "nickname", Lists.emptyList(), "localId", "1aB!1aB")

        /**
         * then
         */
        assertEquals(false, member1.checkPassword("1aB!1aC"))
    }

    @Test
    @DisplayName("패스워드 체크 성공 테스트")
    fun checkPasswordSuccess() {
        /**
         * given
         * 테스트에 필요한 멤버
         */
        val member1 = LocalMember("id", "nickname", Lists.emptyList(), "localId", "1aB!1aB")

        /**
         * then
         */
        assertEquals(true, member1.checkPassword("1aB!1aB"))
    }
}
