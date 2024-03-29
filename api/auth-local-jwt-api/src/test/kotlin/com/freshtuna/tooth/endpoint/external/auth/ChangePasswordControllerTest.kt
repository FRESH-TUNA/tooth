package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.auth.incoming.ToothAuthenticationManager
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.id.ID

import com.freshtuna.tooth.member.incoming.ChangeLocalPasswordUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ChangePasswordControllerTest {

    private val useCase: ChangeLocalPasswordUseCase = mockk()
    private val userDetailManager: ToothAuthenticationManager = mockk()
    private val mockMvc =  MockMvcBuilders
        .standaloneSetup(ChangePasswordController(useCase, userDetailManager))
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
        every { userDetailManager.currentMemberID() } returns ID("thisisId")

        every {
            useCase.change(any(), any(), any())
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
