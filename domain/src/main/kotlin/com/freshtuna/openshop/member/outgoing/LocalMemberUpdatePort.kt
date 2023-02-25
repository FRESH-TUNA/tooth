package com.freshtuna.openshop.member.outgoing

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password

interface LocalMemberUpdatePort {

    /**
     * localMember 정보 변경
     */
    fun changePassword(member: LocalMember, newPassword: Password)
}
