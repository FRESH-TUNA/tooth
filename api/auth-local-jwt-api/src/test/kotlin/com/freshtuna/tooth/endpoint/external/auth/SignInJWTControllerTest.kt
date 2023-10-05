package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.auth.incoming.SignInUseCase
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.token.AuthToken
import com.freshtuna.tooth.member.Member
import com.freshtuna.tooth.member.Password
import com.freshtuna.tooth.token.result.AuthResult
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.collections.ArrayList


class SignInJWTControllerTest {

    private val SignInUseCase: SignInUseCase = mockk()

    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(SignInJWTController(SignInUseCase))
        .build()

    @Test
    fun signIn() {
        // given: localId, password
        val localId = "localId"
        val password = Password("password")
        val localMember = Member(ID("id"), ID("publicId"), ArrayList())
        val payload = """
            {
                "id": "$localId",
                "password": "$password"
            }
            """.trimIndent()

        every {
            SignInUseCase.signIn(any())
        } returns AuthResult(
            AuthToken.of("accessToken"), AuthToken.of("refreshToken")
        )

        // when, then
        mockMvc.perform(post(Url.EXTERNAL.JWT_LOCAL_SIGNIN)
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
