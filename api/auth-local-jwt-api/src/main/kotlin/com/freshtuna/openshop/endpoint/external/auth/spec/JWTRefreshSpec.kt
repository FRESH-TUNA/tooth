package com.freshtuna.openshop.endpoint.external.auth.spec

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.endpoint.external.auth.request.JWTRefreshRequest
import jakarta.servlet.http.HttpServletResponse

interface JWTRefreshSpec {

    fun refresh(request: JWTRefreshRequest, response: HttpServletResponse): BasicResponse
}
