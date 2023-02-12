package com.freshtuna.openshop.endpoint.external.auth.spec

import com.freshtuna.openshop.endpoint.external.auth.request.LocalMemberSignUpRequest
import com.freshtuna.openshop.responses.base.BasicResponse
import jakarta.servlet.http.HttpServletResponse

interface LocalMemberSignInSpec {
    fun signIn(request: LocalMemberSignUpRequest, response: HttpServletResponse): BasicResponse
}