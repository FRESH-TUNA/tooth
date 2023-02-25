package com.freshtuna.openshop.endpoint.external

import com.freshtuna.openshop.api.response.BasicResponse
import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.member.incoming.ChangePasswordUseCase
import com.freshtuna.openshop.config.constant.Url
import com.freshtuna.openshop.endpoint.external.request.ChangePasswordRequest

import com.freshtuna.openshop.member.LocalMember
import com.freshtuna.openshop.endpoint.external.spec.PasswordChangeSpec
import com.freshtuna.openshop.member.Password

import com.freshtuna.openshop.api.security.ToothUserDetailManager

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PasswordChangeController(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val userDetailManager: ToothUserDetailManager
) : PasswordChangeSpec {

    @PostMapping(Url.EXTERNAL.CHANGE_PASSWORD)
    override fun changePassword(
        @RequestBody request: ChangePasswordRequest
    ): BasicResponse {

        changePasswordUseCase.changePassword(
            LocalMember.ofId(userDetailManager.get().username),
            Password(request.curPassword),
            Password(request.newPassword))

        return MessageResponse.OK
    }
}
