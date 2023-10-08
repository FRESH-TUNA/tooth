package com.freshtuna.tooth.member.outgoing

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.Member

interface MemberSearchPort {

    fun findByPublicID(publicID: ID): Member
}
