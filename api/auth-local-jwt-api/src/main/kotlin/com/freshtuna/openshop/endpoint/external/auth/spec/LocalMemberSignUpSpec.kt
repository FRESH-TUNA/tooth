package com.freshtuna.openshop.endpoint.external.auth.spec

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.endpoint.external.auth.request.LocalSignUpRequest
import jakarta.servlet.http.HttpServletResponse

interface LocalMemberSignUpSpec {
    fun signUp(request: LocalSignUpRequest, response: HttpServletResponse): BasicResponse
}
