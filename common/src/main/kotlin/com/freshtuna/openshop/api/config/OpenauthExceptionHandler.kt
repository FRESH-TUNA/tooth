package com.freshtuna.openshop.api.config

import com.freshtuna.openshop.api.response.MessageResponse
import com.freshtuna.openshop.exception.OpenAuthorizationException
import com.freshtuna.openshop.exception.OpenAuthorizationMsgException
import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.exception.OpenMsgException
import com.freshtuna.openshop.exception.constant.Oh
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*


@ControllerAdvice
class OpenauthExceptionHandler {

    @ExceptionHandler(OpenMsgException::class)
    protected fun handleOpenMsgException(e: OpenMsgException): ResponseEntity<*>
        = ResponseEntity.badRequest().body(MessageResponse(e.oh.code, e.msg))


    @ExceptionHandler(OpenException::class)
    protected fun handleOpenException(e: OpenException): ResponseEntity<*>
        = ResponseEntity.badRequest().body(MessageResponse(e.oh.code, e.oh.explanation))


    @ExceptionHandler(OpenAuthorizationMsgException::class)
    protected fun handleOpenAuthenticationMsgException(e: OpenAuthorizationMsgException): ResponseEntity<*>
        = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Optional.of(MessageResponse(e.oh.code, e.msg)))


    @ExceptionHandler(OpenAuthorizationException::class)
    protected fun handleOpenAuthenticationException(e: OpenAuthorizationException): ResponseEntity<*>
        = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MessageResponse(e.oh.code, e.oh.explanation))

    @ExceptionHandler(RuntimeException::class)
    protected fun handleInternalServerError(e: RuntimeException): ResponseEntity<*>
            = ResponseEntity.internalServerError()
                .body(MessageResponse(Oh.INTERNAL_SERVER_ERROR.code, Oh.INTERNAL_SERVER_ERROR.explanation))
}
