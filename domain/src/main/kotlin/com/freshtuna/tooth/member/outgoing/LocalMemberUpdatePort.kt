package com.freshtuna.tooth.member.outgoing

import com.freshtuna.tooth.member.LocalMember
import com.freshtuna.tooth.member.Password

interface LocalMemberUpdatePort {

    /**
     * localMember 정보 변경
     */
    fun changePassword(member: LocalMember, newPassword: Password)
}
