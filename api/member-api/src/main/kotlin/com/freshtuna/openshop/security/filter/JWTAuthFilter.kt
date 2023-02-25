package com.freshtuna.openshop.security.filter

import com.freshtuna.openshop.api.util.HeaderUtil
import com.freshtuna.openshop.config.constant.Env
import com.freshtuna.openshop.jwt.JWT
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import com.freshtuna.openshop.api.security.ToothUserDetail
import jakarta.servlet.FilterChain

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthFilter(
    private val jwtUseCase: JWTUseCase
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {


        val token = HeaderUtil.getAuthorizationHeaderValue(response)

        if (!StringUtils.hasText(token)) {
//            log.debug("토큰 정보 없음")
            return
        }

        val jwt = JWT(token!!)

        jwtUseCase.isValid(jwt)
        if (!jwtUseCase.isValid(jwt)) {
//            log.debug("유효하지 않은 토큰")
            return
        }

        val authorities: List<SimpleGrantedAuthority> = jwtUseCase.claim(jwt, Env.JWT_ROLE_KEY)
            .split(",")
            .map { s -> SimpleGrantedAuthority(s) }

        val userDetail = ToothUserDetail(
            jwtUseCase.idOfToken(jwt),
            authorities
        )

        val authentication: Authentication = UsernamePasswordAuthenticationToken(
            userDetail, "", authorities
        )

        SecurityContextHolder.getContext().setAuthentication(authentication)
//                log.debug("Security Context => 인증 정보 저장 완료: {}", authentication.getName())


        filterChain.doFilter(request, response)
    }
}
