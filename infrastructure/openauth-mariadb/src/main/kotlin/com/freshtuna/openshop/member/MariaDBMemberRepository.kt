package com.freshtuna.openshop.member

import org.springframework.data.jpa.repository.JpaRepository

interface MariaDBMemberRepository : JpaRepository<MariaDBMember, Long> {
}