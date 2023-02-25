package com.freshtuna.openshop.member.outgoing

import com.freshtuna.openshop.member.OAuthMember

interface MemberUpdatePort {
    /**
     * 로컬 계정의 정보를 업데이트 한다.
     */
    fun updateMember(member: OAuthMember)
}
