package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.DataResponse

import com.freshtuna.openshop.api.util.HeaderUtil.Companion.addHeader
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.endpoint.external.auth.request.SignInRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignInSpec
import com.freshtuna.openshop.auth.incoming.LocalSignInUseCase
import com.freshtuna.openshop.auth.result.SignInJWTResult
import com.freshtuna.openshop.endpoint.external.auth.response.SignInJWTResponse

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalSignInJWTController(
    private val LocalSignInUseCase: LocalSignInUseCase
) : LocalMemberSignInSpec {

    /** 일반 로그인  */
    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNIN)
    override fun signIn(@RequestBody request: SignInRequest,
                        response: HttpServletResponse): BasicResponse {

        val result = LocalSignInUseCase.signIn(request.toCommand()) as SignInJWTResult

        addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return DataResponse.of(SignInJWTResponse(result.refreshToken.tokenString))
    }
}
