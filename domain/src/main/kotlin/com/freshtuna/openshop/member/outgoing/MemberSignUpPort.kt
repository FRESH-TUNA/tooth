package com.freshtuna.openshop.member.outgoing

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.OAuthMember

interface MemberSignUpPort {
    /**
     * 대이터 엑세스 계층에 새로운 로컬벰버 저장을 요청한다.
     * 저장이 성공하면 true를 반환한다.
     */
    fun signUp(localMember: LocalMember) : Boolean

    /**
     * 대이터 엑세스 계층에 새로운 oauth 멤버 저장을 요청한다.
     * 저장이 성공하면 true를 반환한다.
     */
    fun signUp(localMember: OAuthMember) : Boolean
}
