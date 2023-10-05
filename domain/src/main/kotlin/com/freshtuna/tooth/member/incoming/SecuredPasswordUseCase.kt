package com.freshtuna.tooth.member.incoming

import com.freshtuna.tooth.member.Password

interface SecuredPasswordUseCase {
    fun encrypt(password: Password)

    fun matched(givenPW: Password, storedPW: Password): Boolean
}
