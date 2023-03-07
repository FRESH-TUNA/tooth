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
class LocalSignUpJWTControllerIntegrationTest {

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

    @Test
    @DisplayName("로컬 회원가입 통합 테스트")
    fun operationIdiot() {
        /**
         * given
         */
        var id = "local1@example.com"
        var requestBody = mapOf(
            "id" to id,
            "password" to "local1",
            "repeatPassword" to "local1"
        )

        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        var entity = HttpEntity(requestBody, headers)

        var responseEntity = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, BasicResponse::class.java)

        id = "local2@example.com"
        requestBody = mapOf(
            "id" to id,
            "password" to "local2",
            "repeatPassword" to "local2"
        )

        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        entity = HttpEntity(requestBody, headers)

        responseEntity = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, BasicResponse::class.java)

        id = "local3@example.com"
        requestBody = mapOf(
            "id" to id,
            "password" to "local3",
            "repeatPassword" to "local3"
        )

        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        entity = HttpEntity(requestBody, headers)

        responseEntity = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, BasicResponse::class.java)

        id = "local4@example.com"
        requestBody = mapOf(
            "id" to id,
            "password" to "local4",
            "repeatPassword" to "local4"
        )

        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        entity = HttpEntity(requestBody, headers)

        responseEntity = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, BasicResponse::class.java)

        id = "local5@example.com"
        requestBody = mapOf(
            "id" to id,
            "password" to "local5",
            "repeatPassword" to "local5"
        )

        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        entity = HttpEntity(requestBody, headers)

        responseEntity = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_LOCAL_SIGNUP, entity, BasicResponse::class.java)

        /**
         * then and cleaning
         */
//        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
//        assertThat(repository.findByLocalId(id).isPresent).isTrue
        //repository.delete(repository.findByLocalId(id).get())
    }
}
