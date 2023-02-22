package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role
import jakarta.persistence.*

@Entity
class MariaDBRole(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @ManyToOne
    var member: MariaDBMember,

    @Column
    var memberRole: Role) {

}
