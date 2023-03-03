package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.api.util.CookieUtil.Companion.addCookie
import com.freshtuna.openshop.api.util.HeaderUtil.Companion.addHeader
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.endpoint.external.auth.request.LocalSignInRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignInSpec
import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase
import com.freshtuna.openshop.config.constant.Env
import com.freshtuna.openshop.member.Password

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalJWTSignInController(
    private val signInJWTUseCase: SignInJWTUseCase
) : LocalMemberSignInSpec {

    /** 일반 로그인  */
    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNIN)
    override fun signIn(@RequestBody request: LocalSignInRequest,
                        response: HttpServletResponse): BasicResponse {

        val result = signInJWTUseCase.signIn(request.id, Password(request.password))

        addCookie(Env.REFRESH_TOKEN_COOKIE, result.refreshToken.tokenString, response)
        addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return MessageResponse.OK
    }
}
