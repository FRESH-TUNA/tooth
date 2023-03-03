package com.freshtuna.openshop.api.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ToothUserDetailManagerImpl : ToothUserDetailManager {

    override fun get(): ToothUserDetail {
        return SecurityContextHolder.getContext().authentication as ToothUserDetail
    }
}
