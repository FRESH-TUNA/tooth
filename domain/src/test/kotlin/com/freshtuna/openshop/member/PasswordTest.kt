package com.freshtuna.openshop.member

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PasswordTest {

    @Test
    @DisplayName("생성 테스트")
    fun create() {
        val password = "password"

        Password(password)
    }
}
