package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.auth.incoming.JWTSignInUseCase
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.auth.result.JWTLocalSignInResult
import com.freshtuna.openshop.member.Member
import com.freshtuna.openshop.member.Password
import com.freshtuna.openshop.member.id.PublicId
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.collections.ArrayList


class SignInJWTControllerTest {

    private val JWTSignInUseCase: JWTSignInUseCase = mockk()

    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(SignInJWTController(JWTSignInUseCase))
        .build()

    @Test
    fun signIn() {
        // given: localId, password
        val localId = "localId"
        val password = Password("password")
        val localMember = Member(PublicId("id"), ArrayList())
        val payload = """
            {
                "id": "$localId",
                "password": "$password"
            }
            """.trimIndent()

        every {
            JWTSignInUseCase.signIn(any())
        } returns JWTLocalSignInResult(
            localMember, JWT.accessOf("accessToken"), JWT.refreshOf("refreshToken")
        )

        // when, then
        mockMvc.perform(post(Url.EXTERNAL.JWT_LOCAL_SIGNIN)
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
