package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.AuthJWTApplication
import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.member.repository.MariaDBLocalMemberRepository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [AuthJWTApplication::class]
)
@ActiveProfiles("sandbox")
class LocalSignUpJWTControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var repository: MariaDBLocalMemberRepository

    @Test
    @DisplayName("로컬 회원가입 통합 테스트")
    fun signupSuccessTest() {
        /**
         * given
         */
        val id = "hohohg@gmail.com"
        val requestBody = mapOf(
            "id" to id,
            "password" to "1aB!1aBc",
            "repeatPassword" to "1aB!1aBc"
        )

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         */
        val responseEntity = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, BasicResponse::class.java)

        /**
         * then and cleaning
         */
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(repository.findByLocalId(id).isPresent).isTrue
        repository.delete(repository.findByLocalId(id).get())
    }
}
