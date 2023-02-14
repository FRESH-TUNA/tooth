package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.api.util.CookieUtil.Companion.addCookie
import com.freshtuna.openshop.api.util.HeaderUtil.Companion.addHeader
import com.freshtuna.openshop.config.UrlConfig
import com.freshtuna.openshop.endpoint.external.auth.request.LocalSignInRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignInSpec
import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class LocalJWTSignInController(
    private val signInJWTUseCase: SignInJWTUseCase
) : LocalMemberSignInSpec {

    companion object {
        private const val REFRESH_TOKEN_COOKIE = "refresh-token"
    }

    /** 일반 로그인  */
    @PostMapping(UrlConfig.EXTERNAL.JWT_LOCAL_SIGNIN)
    override fun signIn(@RequestBody request: LocalSignInRequest,
                        response: HttpServletResponse): BasicResponse {
        val result = signInJWTUseCase.signInLocalMember(request.id, request.password)

        addCookie(REFRESH_TOKEN_COOKIE, result.refreshToken.tokenString, response)
        addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return MessageResponse.OK
    }
}
