package com.freshtuna.openshop.member.outgoing

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.SecuredPassword

interface LocalMemberUpdatePort {

    /**
     * localMember 정보 변경
     */
    fun changePassword(member: LocalMember, newPassword: SecuredPassword)
}
