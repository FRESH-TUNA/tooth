package com.freshtuna.openshop.endpoint.external

import com.freshtuna.openshop.api.security.ToothUserDetail
import com.freshtuna.openshop.api.security.ToothUserDetailManager
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.member.Password

import com.freshtuna.openshop.member.incoming.ChangePasswordUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class PasswordChangeControllerTest {

    private val useCase: ChangePasswordUseCase = mockk()
    private val userDetailManager: ToothUserDetailManager = mockk()
    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(PasswordChangeController(useCase, userDetailManager))
        .build()

    @Test
    @DisplayName("패스워드 변경 요청 테스트")
    fun changePassword() {

        // given: localId, password
        val payload = """
            {
                "curPassword": "curPassword",
                "newPassword": "newPassword",
                "newPasswordRepeat": "newPassword"
            }
            """.trimIndent()

        // when
        every { userDetailManager.get() } returns ToothUserDetail("thisisId", ArrayList())

        every {
            useCase.changePassword(any(), any(), any())
        } returns Unit

        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post(Url.EXTERNAL.CHANGE_PASSWORD)
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
