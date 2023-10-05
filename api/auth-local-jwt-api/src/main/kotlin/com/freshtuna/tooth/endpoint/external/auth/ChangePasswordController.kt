package com.freshtuna.tooth.endpoint.external.auth

import com.freshtuna.tooth.api.response.BasicResponse
import com.freshtuna.tooth.api.response.MessageResponse
import com.freshtuna.tooth.member.incoming.ChangeLocalPasswordUseCase
import com.freshtuna.tooth.config.constant.Url
import com.freshtuna.tooth.endpoint.external.auth.request.ChangePasswordRequest
import com.freshtuna.tooth.endpoint.external.auth.spec.ChangePasswordSpec

import com.freshtuna.tooth.member.Password

import com.freshtuna.tooth.auth.incoming.ToothAuthenticationManager

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ChangePasswordController(
    private val changeLocalPasswordUseCase: ChangeLocalPasswordUseCase,
    private val authManager: ToothAuthenticationManager
) : ChangePasswordSpec {

    @PostMapping(Url.EXTERNAL.CHANGE_PASSWORD)
    override fun changePassword(
        @RequestBody request: ChangePasswordRequest
    ): BasicResponse {

        changeLocalPasswordUseCase.change(
            authManager.currentMemberID(),
            Password(request.curPassword),
            Password(request.newPassword))

        return MessageResponse.OK
    }
}
