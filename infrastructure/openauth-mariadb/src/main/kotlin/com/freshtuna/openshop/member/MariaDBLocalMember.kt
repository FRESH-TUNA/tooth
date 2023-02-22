package com.freshtuna.openshop.member

import jakarta.persistence.*
import java.util.*

@Entity
@DiscriminatorValue("LOCAL")
class MariaDBLocalMember(

    @Column
    var localId: String,

    @Column
    var password: String,

    id: Long?,
    publicId: UUID?,
    nickname: String,
    roles: List<MariaDBRole>,

    ) : MariaDBMember(id, publicId, nickname, roles) {

    fun toLocalMember(): LocalMember {
        val domainRules = roles.map { mariaDBRole -> mariaDBRole.memberRole }

        return LocalMember(publicId.toString(), nickname, domainRules, localId, password)
    }
}

/**
 * LocalMember to MariaDBLocalMember
 */
fun LocalMember.toMariaDBLocalMember(): MariaDBLocalMember {
    val publicId = if(Objects.isNull(this.id)) null else UUID.fromString(this.id)

    val mariaDBLocalMember = MariaDBLocalMember(
        this.localId,
        this.password,
        null,
        publicId,
        this.nickname,
        Collections.emptyList()
    )

    mariaDBLocalMember.roles = this.roles.map { role -> MariaDBRole(null, mariaDBLocalMember, role) }

    return mariaDBLocalMember
}
