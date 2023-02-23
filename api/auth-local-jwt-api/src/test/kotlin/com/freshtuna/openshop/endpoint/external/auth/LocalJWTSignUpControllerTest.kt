package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.auth.incoming.LocalSignUpJWTUseCase
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.member.Member
import io.mockk.every
import io.mockk.mockk

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class LocalJWTSignUpControllerTest {

    private val signUpJWTUseCase: LocalSignUpJWTUseCase = mockk()

    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(LocalJWTSignUpController(signUpJWTUseCase))
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
        val localMember = Member("id", "nick", ArrayList())
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
        } returns JWTResult(
            localMember, JWT("accessToken"), JWT("refreshToken")
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
