package com.freshtuna.openshop.member.repository

import com.freshtuna.openshop.member.entity.MariaDBMember
import org.springframework.data.jpa.repository.JpaRepository

interface MariaDBMemberRepository : JpaRepository<MariaDBMember, Long> {
}