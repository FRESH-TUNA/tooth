package com.freshtuna.tooth.member.repository

import com.freshtuna.tooth.member.entity.MariaDBMember
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MariaDBMemberRepository : JpaRepository<MariaDBMember, Long> {

    fun findByPublicId(id: UUID): MariaDBMember
}
