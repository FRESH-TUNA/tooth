package com.freshtuna.tooth.member.entity

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.member.LocalMember

import com.freshtuna.tooth.member.Password
import jakarta.persistence.*
import java.util.*

@Entity
@DiscriminatorValue("LOCAL")
class MariaDBLocalMember(

    @Column
    var localId: String,

    @Column(name = "local_password")
    var password: String,

    nickname: String?,
) : MariaDBMember(nickname) {
    companion object {

        fun of(command: SignUpCommand): MariaDBLocalMember {

            val mariaDBLocalMember = MariaDBLocalMember(
                command.localId.toString(),
                command.password.passwordString,
                null
            )

            mariaDBLocalMember.updateRoles(command.roles)

            return mariaDBLocalMember
        }
    }

    fun toLocalMember(): LocalMember {
        val domainRules = roles.map { mariaDBRole -> mariaDBRole.role }

        return LocalMember(
            ID(id),
            ID(publicId),
            domainRules,
            ID(localId),
            Password(password)
        )
    }

    fun changePassword(newPassword: Password) {
        password = newPassword.passwordString
    }
}
