package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.api.util.CookieUtil
import com.freshtuna.openshop.api.util.HeaderUtil
import com.freshtuna.openshop.auth.incoming.LocalSignUpJWTUseCase
import com.freshtuna.openshop.config.constant.Env
import com.freshtuna.openshop.config.constant.Url

import com.freshtuna.openshop.endpoint.external.auth.request.LocalSignUpRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignUpSpec
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalJWTSignUpController(
    private val signUpJWTUseCase: LocalSignUpJWTUseCase
) : LocalMemberSignUpSpec {
    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNUP)
    override fun signUp(@RequestBody request: LocalSignUpRequest,
                        response: HttpServletResponse): BasicResponse {
        val result = signUpJWTUseCase.signUp(request.toNewMember(), request.toPassword())

        CookieUtil.addCookie(Env.REFRESH_TOKEN_COOKIE, result.refreshToken.tokenString, response)
        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return MessageResponse.OK
    }
}
