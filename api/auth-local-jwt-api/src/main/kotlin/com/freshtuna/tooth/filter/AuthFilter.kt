package com.freshtuna.tooth.filter

import com.freshtuna.tooth.util.HeaderUtil
import com.freshtuna.tooth.token.AuthToken
import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import com.freshtuna.tooth.security.userDetail.ToothUserDetail
import io.github.oshai.KotlinLogging
import jakarta.servlet.FilterChain

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
    private val tokenManager: ManageTokenUseCase
) : OncePerRequestFilter() {

    private val log = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = HeaderUtil.getAuthorizationHeaderValue(request)

        if (!StringUtils.hasText(token)) {
            // log.info("토큰 정보 없음")
            filterChain.doFilter(request, response)
            return
        }

        val member = tokenManager.identify(AuthToken.of(token!!))

        val userDetail = ToothUserDetail(member)

        val authentication: Authentication = UsernamePasswordAuthenticationToken(
            userDetail, "", userDetail.authorities
        )

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
