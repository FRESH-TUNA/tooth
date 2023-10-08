package com.freshtuna.tooth.endpoint.external.auth.spec

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.endpoint.external.auth.request.ChangePasswordRequest

interface ChangePasswordSpec {

    fun changePassword(
        request: ChangePasswordRequest
    ) : BasicResponse
}
