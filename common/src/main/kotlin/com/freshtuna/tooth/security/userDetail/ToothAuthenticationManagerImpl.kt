package com.freshtuna.tooth.security.userDetail

import com.freshtuna.tooth.auth.incoming.ToothAuthenticationManager
import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.Member
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class ToothAuthenticationManagerImpl : ToothAuthenticationManager {

    override fun currentMember(): Member {
        return (SecurityContextHolder.getContext().authentication.principal as ToothUserDetail).member
    }

    override fun currentMemberID(): ID {
        return currentMember().id
    }
}
