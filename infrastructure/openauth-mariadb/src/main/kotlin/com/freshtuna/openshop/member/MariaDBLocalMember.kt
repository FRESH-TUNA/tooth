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

    id: Long,
    publicId: UUID,
    nickname: String,
    roles: List<MariaDBRole>,

) : MariaDBMember(id, publicId, nickname, roles) {

    fun toLocalMember(): LocalMember {
        val domainRules = roles.map { mariaDBRole -> mariaDBRole.memberRole }

        return LocalMember(id.toString(), nickname, domainRules, localId, password)
    }
}
