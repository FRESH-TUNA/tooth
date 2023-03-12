package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.DataResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.api.util.CookieUtil
import com.freshtuna.openshop.api.util.HeaderUtil
import com.freshtuna.openshop.auth.command.LocalSignUpCommand
import com.freshtuna.openshop.auth.incoming.JWTSignUpUseCase
import com.freshtuna.openshop.config.constant.Env
import com.freshtuna.openshop.config.constant.Url

import com.freshtuna.openshop.endpoint.external.auth.request.SignUpRequest
import com.freshtuna.openshop.endpoint.external.auth.response.LocalSignInJWTResponse
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignUpSpec
import io.github.oshai.KotlinLogging
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpJWTController(
    private val signUpJWTUseCase: JWTSignUpUseCase
) : LocalMemberSignUpSpec {

    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNUP)
    override fun signUp(@RequestBody request: SignUpRequest,
                        response: HttpServletResponse): BasicResponse {

        val result = signUpJWTUseCase.signUp(request.toCommand())

        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)
        return DataResponse.of(LocalSignInJWTResponse(result.refreshToken.tokenString))
    }
}
