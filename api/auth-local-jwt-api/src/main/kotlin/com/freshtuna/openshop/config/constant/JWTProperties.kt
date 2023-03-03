package com.freshtuna.openshop.config.constant

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "openauth.jwt")
data class JWTProperties(
    var secret: String = "",
    var refreshTokenSecret: String = "",
    var accessTokenExpiredMileSeconds: String = "",
    var refreshTokenExpiredMileSeconds: String = ""
)
