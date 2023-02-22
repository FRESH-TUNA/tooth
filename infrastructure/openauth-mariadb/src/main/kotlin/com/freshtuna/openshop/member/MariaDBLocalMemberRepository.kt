package com.freshtuna.openshop.member

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MariaDBLocalMemberRepository  : JpaRepository<MariaDBLocalMember, Long> {

    fun existsByLocalId(localId: String): Boolean

    fun findByLocalId(localId: String): Optional<MariaDBLocalMember>
}
