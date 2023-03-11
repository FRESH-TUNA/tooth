package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.AuthJWTApplication
import com.freshtuna.openshop.api.response.DataResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.config.constant.Url
import io.github.oshai.KotlinLogging
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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
class RefreshJWTControllerSystemTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Value("\${openauth.jwt.local-member-refresh-token-for-test}")
    private lateinit var refreshToken: String

    private val logger = KotlinLogging.logger {}

    @Test
    @DisplayName("토큰 리프레시 통합 테스트")
    fun refresh() {
        /**
         * given
         */
        val requestBody = mapOf(
            "refresh" to refreshToken,
        )
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestBody, headers)

        /**
         * when
         *
         */
        val response = restTemplate.postForEntity(
            Url.EXTERNAL.JWT_REFRESH, entity, MessageResponse::class.java)

        /**
         * then
         */
        logger.info(refreshToken)
        logger.info(response.body!!.message)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.headers[HttpHeaders.AUTHORIZATION])
    }
}
