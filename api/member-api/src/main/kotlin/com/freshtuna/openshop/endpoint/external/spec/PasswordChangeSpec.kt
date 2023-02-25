package com.freshtuna.openshop.endpoint.external.spec

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.endpoint.external.request.ChangePasswordRequest
import com.freshtuna.openshop.api.security.ToothUserDetail

interface PasswordChangeSpec {

    fun changePassword(
        request: ChangePasswordRequest
    ) : BasicResponse
}
