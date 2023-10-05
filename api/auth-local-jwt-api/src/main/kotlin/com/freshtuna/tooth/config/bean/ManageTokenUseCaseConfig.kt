package com.freshtuna.tooth.config.bean

import com.freshtuna.tooth.config.constant.JWTProperties
import com.freshtuna.tooth.member.outgoing.MemberSearchPort
import com.freshtuna.tooth.token.TokenManager
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ManageTokenUseCaseConfig(
    private val jwtProperties: JWTProperties,
    private val memberSearchPort: MemberSearchPort
) {
    @Bean
    fun tokenManager(): ManageTokenUseCase {

        return TokenManager(
            Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray()),
            jwtProperties.accessTokenExpiredMileSeconds.toLong(),
            jwtProperties.roleKey,
            jwtProperties.prefix,
            memberSearchPort
        )
    }

    @Bean
    fun refreshTokenManager(): ManageTokenUseCase {

        return TokenManager(
            Keys.hmacShaKeyFor(jwtProperties.refreshTokenSecret.toByteArray()),
            jwtProperties.refreshTokenExpiredMileSeconds.toLong(),
            jwtProperties.roleKey,
            jwtProperties.prefix,
            memberSearchPort
        )
    }
}
