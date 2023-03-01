package com.freshtuna.openshop.member.incoming

import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.SecuredPassword

interface SecuredPasswordUseCase {
    fun generate(password: Password): SecuredPassword
}
