package com.freshtuna.openshop.jwt

import com.freshtuna.openshop.exception.constant.Error
import com.freshtuna.openshop.member.Member
import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.exception.OpenMsgException
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*

class JWTService(
    private val key: Key,
    private val accessTokenExpiredMileSeconds: Long,
    private val refreshTokenExpiredMileSeconds: Long) : JWTUseCase {

    constructor(
        key: String,
        accessTokenExpiredMileSeconds: Long,
        refreshTokenExpiredMileSeconds: Long): this(
            Keys.hmacShaKeyFor(key.toByteArray()),
            accessTokenExpiredMileSeconds,
            refreshTokenExpiredMileSeconds
        )

    override fun generateAccessToken(member: Member): JWT {
        val roles: String = member.roles
            .map { role -> role.name }
            .joinToString(separator = ",")
        val now: Long = Date().time
        val expiryDate = Date(now + accessTokenExpiredMileSeconds)

        return JWT(Jwts.builder()
            .setSubject(member.id)
            .claim("ROLES", roles)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun generateRefreshToken(member: Member): JWT {
        val now: Long = Date().time
        val expiryDate = Date(now + refreshTokenExpiredMileSeconds)

        return JWT(Jwts.builder()
            .setSubject(member.id)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun isValid(token: JWT): Boolean {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.tokenString)
                .body != null
        } catch (e: SecurityException) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.JWT_ERROR)
            throw OpenMsgException(Error.JWT_ERROR, e.message!!)
        } catch (e: JwtException) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.JWT_ERROR)
            throw OpenMsgException(Error.JWT_ERROR, e.message!!)
        } catch (e: IllegalArgumentException) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.JWT_ERROR)
            throw OpenMsgException(Error.JWT_ERROR, e.message!!)
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Error.INTERNAL_SERVER_ERROR, e.message!!)
        }

        return false
    }

    override fun idOfToken(token: JWT): String {
        isValid(token)

        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.tokenString)
                .getBody()
                .subject
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Error.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Error.INTERNAL_SERVER_ERROR, e.message!!)
        }

        return ""
    }
}

