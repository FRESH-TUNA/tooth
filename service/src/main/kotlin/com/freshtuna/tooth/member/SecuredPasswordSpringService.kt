package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.incoming.SecuredPasswordUseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecuredPasswordSpringService(
    private val passwordEncoder: PasswordEncoder
) : SecuredPasswordUseCase {

    override fun encrypt(password: Password) {
        password.passwordString = passwordEncoder.encode(password.passwordString)
    }

    override fun matched(givenPW: Password, storedPW: Password): Boolean {
        return passwordEncoder.matches(givenPW.passwordString, storedPW.passwordString)
    }
}
