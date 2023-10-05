package com.freshtuna.tooth.token.incoming

import com.freshtuna.tooth.token.AuthToken
import com.freshtuna.tooth.member.Member

interface ManageTokenUseCase {
    /**
     * 토큰을 발행한다.
     */
    fun generate(member: Member): AuthToken


    /**
     * 토큰의 정보로 엠버 정보를 조회한다.
     */
    fun identify(token: AuthToken): Member
}
