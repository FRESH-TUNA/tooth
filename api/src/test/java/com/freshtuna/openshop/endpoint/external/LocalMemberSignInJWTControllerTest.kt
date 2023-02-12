package com.freshtuna.openshop.endpoint.external

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.config.UrlConfig
import com.freshtuna.openshop.endpoint.external.auth.LocalMemberSignInJWTController
import com.freshtuna.openshop.jwt.JWTResult
import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*


/**
 * https://www.journeytoawebapp.com/posts/testing-with-junit
 */
class LocalMemberSignInJWTControllerTest {

    private var signInJWTUseCase: SignInJWTUseCase = mockk()

    private var mockMvc: MockMvc = MockMvcBuilders
        .standaloneSetup(LocalMemberSignInJWTController(signInJWTUseCase))
        .
        .build()

    @Test
    @DisplayName("로컬 계정 로그인 테스트")
    fun localSigninTest() {
        // given
        val localId = "thisIsLocalId"
        val password = "password"

        every { signInJWTUseCase.signInLocalMember(localId, password) } returns JWTResult(
            LocalMember("id", "name", "nickname", Collections.emptyList(), localId, password),
            "thisIsAccessToken",
            "thisIsRefreshToken"
        )

        // when & then
        val requestBody = "{\"id\":\"thisIsLocalId\", \"password\": \"password\"}";

        val request = MockMvcRequestBuilders
            .post(UrlConfig.EXTERNAL.JWT_LOCAL_SIGNIN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody)

        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }
}