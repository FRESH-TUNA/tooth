package com.freshtuna.tooth.member.outgoing

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.LocalMember

interface LocalMemberSearchPort {
    /**
     * 이미같은 localId로 가입한 로컬 계정이 있는지 검사한다.
     */
    fun existsByLocalId(localId: ID) : Boolean

    fun findByLocalId(localId: ID): LocalMember

    fun findByPublicID(publicId: ID): LocalMember

    fun findBy(id: ID): LocalMember
}
