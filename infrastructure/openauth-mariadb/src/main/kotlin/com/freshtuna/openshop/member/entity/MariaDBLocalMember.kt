package com.freshtuna.openshop.member.entity

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.SecuredPassword
import jakarta.persistence.*
import java.util.*

@Entity
@DiscriminatorValue("LOCAL")
class MariaDBLocalMember(

    @Column
    var localId: String,

    @Column(name = "local_password")
    var password: String,

    nickname: String,
) : MariaDBMember(nickname) {
    companion object {

        fun of(localMember: LocalMember, securedPassword: SecuredPassword): MariaDBLocalMember {

            val mariaDBLocalMember = MariaDBLocalMember(
                localMember.localId,
                securedPassword.passwordString,
                localMember.nickname
            )

            mariaDBLocalMember.updateRoles(localMember.roles)

            return mariaDBLocalMember
        }
    }

    fun toLocalMember(): LocalMember {
        val domainRules = roles.map { mariaDBRole -> mariaDBRole.role }

        return LocalMember(publicId.toString(), nickname, domainRules, localId)
    }

    fun changePassword(newPassword: SecuredPassword) {
        password = newPassword.passwordString
    }
}
