package com.freshtuna.openshop.member.out

import com.freshtuna.openshop.OAuthMember

interface MemberUpdatePort {
    /**
     * 소셜 계정의 정보를 업데이트 한다.
     */
    fun updateMember(member: OAuthMember)
}
