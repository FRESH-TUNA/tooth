package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase
import com.freshtuna.openshop.config.UrlConfig
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.member.Member
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.collections.ArrayList


class LocalJWTSignInControllerTest {

    private val signInJWTUseCase: SignInJWTUseCase = mockk()

    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(LocalJWTSignInController(signInJWTUseCase))
        .build()

    @Test
    fun signIn() {
        // given: localId, password
        val localId = "localId"
        val password = "password"
        val localMember = Member("id", "name", "nick", ArrayList())
        val payload = """
            {
                "id": "$localId",
                "password": "$password"
            }
            """.trimIndent()

        every {
            signInJWTUseCase.signIn(localId, password)
        } returns JWTResult(
            localMember, JWT("accessToken"), JWT("refreshToken")
        )

        // when, then
        mockMvc.perform(post(UrlConfig.EXTERNAL.JWT_LOCAL_SIGNIN)
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}