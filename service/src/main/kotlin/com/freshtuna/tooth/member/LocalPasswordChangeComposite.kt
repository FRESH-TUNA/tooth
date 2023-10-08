package com.freshtuna.tooth.member

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.incoming.ChangeLocalPasswordUseCase
import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import com.freshtuna.tooth.member.outgoing.LocalMemberUpdatePort
import com.freshtuna.tooth.member.outgoing.LocalMemberSearchPort
import io.github.oshai.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LocalPasswordChangeComposite(
    private val memberUpdatePort: LocalMemberUpdatePort,
    private val localMemberSearchPort: LocalMemberSearchPort,
    private val securedPasswordUseCase: SecuredPasswordUseCase
) : ChangeLocalPasswordUseCase {


    private val log = KotlinLogging.logger {}

    override fun change(id: ID, curPassword: Password, newPassword: Password) {

        val member = localMemberSearchPort.findBy(id)

        if(!securedPasswordUseCase.matched(curPassword, member.password))
            Oh.localAuthenticationFail()

        if (!newPassword.checkPasswordRule())
            Oh.breakPasswordRule()

        securedPasswordUseCase.encrypt(newPassword)

        memberUpdatePort.changePassword(member, newPassword)
    }
}
