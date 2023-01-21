package com.freshtuna.openshop.member.out

interface MemberSearchPort {
    /**
     * 이미같은 localId로 가입한 로컬 계정이 있는지 검사한다.
     */
    fun existsLocalMemberBylocalId(localId: String) : Boolean
}
