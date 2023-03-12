package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.DataResponse

import com.freshtuna.openshop.api.util.HeaderUtil.Companion.addHeader
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.endpoint.external.auth.request.SignInRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignInSpec
import com.freshtuna.openshop.auth.incoming.JWTSignInUseCase
import com.freshtuna.openshop.endpoint.external.auth.response.LocalSignInJWTResponse

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInJWTController(
    private val JWTSignInUseCase: JWTSignInUseCase
) : LocalMemberSignInSpec {

    /** 일반 로그인  */
    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNIN)
    override fun signIn(@RequestBody request: SignInRequest,
                        response: HttpServletResponse): BasicResponse {

        val result = JWTSignInUseCase.signIn(request.toCommand())

        addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return DataResponse.of(LocalSignInJWTResponse(result.refreshToken.tokenString))
    }
}
