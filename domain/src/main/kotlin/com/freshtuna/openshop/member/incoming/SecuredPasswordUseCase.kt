package com.freshtuna.openshop.member.incoming

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.EncryptedPassword

interface SecuredPasswordUseCase {
    fun generate(password: Password): EncryptedPassword

    fun matched(givenPW: Password, storedPW: EncryptedPassword): Boolean
}
