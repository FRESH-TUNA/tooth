package com.freshtuna.openshop.member.repository

import com.freshtuna.openshop.member.entity.MariaDBLocalMember
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MariaDBLocalMemberRepository  : JpaRepository<MariaDBLocalMember, Long> {

    fun existsByLocalId(localId: String): Boolean

    fun findByLocalId(localId: String): Optional<MariaDBLocalMember>

    fun findByLocalIdAndPassword(localId: String, password: String): Optional<MariaDBLocalMember>

    fun findByPublicId(publicId: UUID): MariaDBLocalMember
}
