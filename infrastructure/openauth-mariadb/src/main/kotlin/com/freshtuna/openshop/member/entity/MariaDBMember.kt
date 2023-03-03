package com.freshtuna.openshop.member.entity

import com.freshtuna.openshop.member.constant.Role
import jakarta.persistence.*

import java.util.UUID

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "member_type")
class MariaDBMember(
    nickname: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = -1

    @GeneratedValue(generator = "uuid2")
    var publicId: UUID = UUID.randomUUID()

    @Column
    var nickname: String = nickname

    @OneToMany
    private var _roles: MutableList<MariaDBRole> = mutableListOf()
    val roles: List<MariaDBRole>
        get() = _roles.toList()

    fun updateRoles(roles: List<Role>) {
        _roles.clear()
        roles.map { role -> this._roles.add(MariaDBRole( this, role)) }
    }
}
