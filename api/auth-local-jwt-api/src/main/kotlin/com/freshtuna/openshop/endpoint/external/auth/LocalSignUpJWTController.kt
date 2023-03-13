package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.DataResponse
import com.freshtuna.openshop.api.util.HeaderUtil
import com.freshtuna.openshop.auth.incoming.LocalSignUpUseCase
import com.freshtuna.openshop.auth.result.SignInJWTResult
import com.freshtuna.openshop.config.constant.Url

import com.freshtuna.openshop.endpoint.external.auth.request.SignUpRequest
import com.freshtuna.openshop.endpoint.external.auth.response.SignInJWTResponse
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignUpSpec
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LocalSignUpJWTController(
    private val signUpJWTUseCase: LocalSignUpUseCase
) : LocalMemberSignUpSpec {

    @PostMapping(Url.EXTERNAL.JWT_LOCAL_SIGNUP)
    override fun signUp(@RequestBody request: SignUpRequest,
                        response: HttpServletResponse): BasicResponse {

        val result = signUpJWTUseCase.signUp(request.toCommand()) as SignInJWTResult

        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, result.accessToken.tokenString, response)

        return DataResponse.of(SignInJWTResponse(result.refreshToken.tokenString))
    }
}
