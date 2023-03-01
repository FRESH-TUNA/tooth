package com.freshtuna.openshop.member.entity

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.SecuredPassword
import jakarta.persistence.*
import java.util.*

@Entity
@DiscriminatorValue("LOCAL")
class MariaDBLocalMember(

    @Column
    var localId: String?,

    @Column
    var password: String?,

    id: Long?,
    publicId: UUID?,
    nickname: String?,
    roles: List<MariaDBRole>,

    ) : MariaDBMember(id, publicId, nickname, roles) {
    companion object {

        fun of(localMember: LocalMember, securedPassword: SecuredPassword): MariaDBLocalMember {
            val publicId = if(Objects.isNull(localMember.id)) null else UUID.fromString(localMember.id)

            val mariaDBLocalMember = MariaDBLocalMember(
                localMember.localId,
                securedPassword.passwordString,
                null,
                publicId,
                localMember.nickname,
                Collections.emptyList()
            )

            mariaDBLocalMember.roles = localMember.roles!!
                .map { role -> MariaDBRole(null, mariaDBLocalMember, role) }

            return mariaDBLocalMember
        }
    }

    fun toLocalMember(): LocalMember {
        val domainRules = roles.map { mariaDBRole -> mariaDBRole.memberRole }

        return LocalMember(publicId.toString(), nickname, domainRules, localId)
    }

    fun changePassword(newPassword: SecuredPassword) {
        password = newPassword.passwordString
    }
}
