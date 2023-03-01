package com.freshtuna.openshop.member.entity

import jakarta.persistence.*

import java.util.UUID

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
open class MariaDBMember(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @GeneratedValue(generator = "uuid2")
    var publicId: UUID?,

    @Column
    var nickname: String?,

    @OneToMany
    var roles: List<MariaDBRole>) {
}
