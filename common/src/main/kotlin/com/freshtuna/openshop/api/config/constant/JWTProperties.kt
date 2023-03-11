package com.freshtuna.openshop.api.config.constant

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "openauth.jwt")
data class JWTProperties(
    var secret: String = "",
    var refreshTokenSecret: String = "",
    var accessTokenExpiredMileSeconds: String = "",
    var refreshTokenExpiredMileSeconds: String = "",
    var roleKey: String = "",
    var prefix: String = ""
)
