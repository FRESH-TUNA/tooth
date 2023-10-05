package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.util.HeaderUtil
import com.freshtuna.tooth.config.constant.Url

import com.freshtuna.tooth.endpoint.external.auth.request.RefreshJWTRequest
import com.freshtuna.tooth.endpoint.external.auth.spec.RefreshJWTSpec
import com.freshtuna.tooth.token.AuthToken
import com.freshtuna.tooth.token.command.RefreshTokenCommand
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.token.incoming.RefreshTokenUseCase
import io.github.oshai.KotlinLogging
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshJWTController(
    private val refreshTokenUseCase: RefreshTokenUseCase
) : RefreshJWTSpec {

    private val log = KotlinLogging.logger {}

    @PostMapping(Url.EXTERNAL.JWT_REFRESH)
    override fun refresh(@RequestBody request: RefreshJWTRequest, response: HttpServletResponse): BasicResponse {

        val command = RefreshTokenCommand(AuthToken.of(request.refresh))

        val refreshResult = refreshTokenUseCase.refresh(command)

        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, refreshResult.authToken.tokenString, response)

        return MessageResponse.OK
    }
}
