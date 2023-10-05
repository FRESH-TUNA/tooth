package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.DataResponse
import com.freshtuna.tooth.util.HeaderUtil
import com.freshtuna.tooth.auth.incoming.SignUpUseCase

import com.freshtuna.tooth.config.constant.Url

import com.freshtuna.tooth.endpoint.external.auth.request.SignUpRequest
import com.freshtuna.tooth.endpoint.external.auth.response.SignInJWTResponse
import com.freshtuna.tooth.endpoint.external.auth.spec.LocalMemberSignUpSpec
import io.github.oshai.KotlinLogging
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpJWTController(
    private val signUpJWTUseCase: SignUpUseCase
) : LocalMemberSignUpSpec {

    private val log = KotlinLogging.logger {}

    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNUP)
    override fun signUp(@RequestBody request: SignUpRequest,
                        response: HttpServletResponse): BasicResponse {

        log.debug("ahhahahaha")

        val result = signUpJWTUseCase.signUp(request.toCommand())

        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, result.access.tokenString, response)

        return DataResponse.of(SignInJWTResponse(result.refresh.tokenString))
    }
}
