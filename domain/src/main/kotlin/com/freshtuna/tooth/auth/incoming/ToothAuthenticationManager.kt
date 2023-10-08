package com.freshtuna.tooth.auth.incoming

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.Member

interface ToothAuthenticationManager {

    fun currentMember(): Member

    fun currentMemberID(): ID
}
