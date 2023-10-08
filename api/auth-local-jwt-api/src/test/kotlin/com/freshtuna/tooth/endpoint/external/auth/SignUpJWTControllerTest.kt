package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.auth.incoming.SignUpUseCase

import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.token.AuthToken
import com.freshtuna.tooth.token.result.AuthResult
import io.mockk.every
import io.mockk.mockk

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class SignUpJWTControllerTest {

    private val signUpJWTUseCase: SignUpUseCase = mockk()

    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(SignUpJWTController(signUpJWTUseCase))
        .build()

    /**
     * 회원가입 테스트
     */
    @Test
    fun signUp() {
        // given: localId, password
        val localId = "localId"
        val password = "password"
        val nickname = "nickname"

        val payload = """
            {
                "id": "$localId",
                "nickname": "$nickname",
                "password": "$password",
                "repeatPassword": "$password"
            }
            """.trimIndent()

        // when
        every {
            signUpJWTUseCase.signUp(any())
        } returns AuthResult(
            AuthToken.of("accessToken"), AuthToken.of("refreshToken")
        )

        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post(Url.EXTERNAL.JWT_LOCAL_SIGNUP)
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
