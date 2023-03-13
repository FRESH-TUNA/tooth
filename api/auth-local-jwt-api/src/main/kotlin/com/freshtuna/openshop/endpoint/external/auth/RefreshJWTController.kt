package com.freshtuna.openshop.endpoint.external.auth

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.api.util.HeaderUtil
import com.freshtuna.openshop.config.constant.Url

import com.freshtuna.openshop.endpoint.external.auth.request.RefreshJWTRequest
import com.freshtuna.openshop.endpoint.external.auth.spec.RefreshJWTSpec
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshJWTController(
    private val jwtUseCase: JWTUseCase
) : RefreshJWTSpec {

    @PostMapping(Url.EXTERNAL.JWT_REFRESH)
    override fun refresh(@RequestBody request: RefreshJWTRequest, response: HttpServletResponse): BasicResponse {
        val newAccessToken = jwtUseCase.refresh(JWT.refreshOf(request.refresh))

        HeaderUtil.addHeader(HttpHeaders.AUTHORIZATION, newAccessToken.tokenString, response)

        return MessageResponse.OK
    }
}
