package com.freshtuna.openshop.endpoint.external.auth.spec

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.endpoint.external.auth.request.RefreshJWTRequest
import jakarta.servlet.http.HttpServletResponse

interface RefreshJWTSpec {

    fun refresh(request: RefreshJWTRequest, response: HttpServletResponse): BasicResponse
}
