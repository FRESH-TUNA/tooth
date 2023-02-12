package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.OpenAbstractController
import com.freshtuna.openshop.config.UrlConfig
import com.freshtuna.openshop.endpoint.external.auth.request.LocalMemberSignUpRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.LocalMemberSignInSpec
import com.freshtuna.openshop.auth.incoming.SignInJWTUseCase
import com.freshtuna.openshop.responses.base.BasicResponse
import com.freshtuna.openshop.responses.base.MessageResponse

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class LocalMemberSignInJWTController(
    private val signInJWTUseCase: SignInJWTUseCase
): OpenAbstractController(), LocalMemberSignInSpec {

    companion object {
        private const val REFRESH_TOKEN_COOKIE = "refresh-token"
    }

    /** 일반 로그인  */
    @PostMapping(UrlConfig.EXTERNAL.JWT_LOCAL_SIGNIN)
    override fun signIn(@RequestBody request: LocalMemberSignUpRequest,
                        response: HttpServletResponse): BasicResponse {
        val result = signInJWTUseCase.signInLocalMember(request.id, request.password)

//        CookieUtils.addRefreshToken(result.refreshToken, response)
//        HeaderUtils.setAuthorization(result.accessToken, response)
        addCookie(REFRESH_TOKEN_COOKIE, result.refreshToken, response)
        addHeader(HttpHeaders.AUTHORIZATION, result.accessToken, response)

        return MessageResponse.OK
    }
}
