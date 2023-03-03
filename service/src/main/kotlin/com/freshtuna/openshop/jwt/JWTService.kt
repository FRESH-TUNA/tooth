package com.freshtuna.openshop.jwt

import com.freshtuna.openshop.exception.constant.Oh
import com.freshtuna.openshop.member.Member
import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.exception.OpenMsgException
import com.freshtuna.openshop.jwt.incoming.JWTUseCase
import io.jsonwebtoken.*
import java.security.Key
import java.util.*

class JWTService(
    private val secret: Key,
    private val refreshTokenSecret: Key,
    private val accessTokenExpiredMileSeconds: Long,
    private val refreshTokenExpiredMileSeconds: Long) : JWTUseCase {

    override fun generateAccessToken(member: Member): JWT {
        val roles: String = member.roles!!.joinToString(separator = ",") { role -> role.name }
        val now: Long = Date().time
        val expiryDate = Date(now + accessTokenExpiredMileSeconds)

        return JWT(Jwts.builder()
            .setSubject(member.id)
            .claim("ROLES", roles)
            .signWith(secret, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun generateRefreshToken(member: Member): JWT {
        val now: Long = Date().time
        val expiryDate = Date(now + refreshTokenExpiredMileSeconds)

        return JWT(Jwts.builder()
            .setSubject(member.id)
            .signWith(refreshTokenSecret, SignatureAlgorithm.HS512)
            .setExpiration(expiryDate)
            .compact())
    }

    override fun isValid(token: JWT): Boolean {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token.tokenString)
                .body != null
        } catch (e: SecurityException) {
            if (Objects.isNull(e.message))
                throw OpenException(Oh.JWT_ERROR)
            throw OpenMsgException(Oh.JWT_ERROR, e.message!!)
        } catch (e: JwtException) {
            if (Objects.isNull(e.message))
                throw OpenException(Oh.JWT_ERROR)
            throw OpenMsgException(Oh.JWT_ERROR, e.message!!)
        } catch (e: IllegalArgumentException) {
            if (Objects.isNull(e.message))
                throw OpenException(Oh.JWT_ERROR)
            throw OpenMsgException(Oh.JWT_ERROR, e.message!!)
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Oh.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Oh.INTERNAL_SERVER_ERROR, e.message!!)
        }

        return false
    }

    override fun idOfToken(token: JWT): String {

        try {
            return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token.tokenString)
                .getBody()
                .subject
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Oh.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Oh.INTERNAL_SERVER_ERROR, e.message!!)
        }
    }

    override fun claim(token: JWT, claimKey: String): String {

        try {
            return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token.tokenString)
                .body[claimKey].toString()
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw OpenException(Oh.INTERNAL_SERVER_ERROR)
            throw OpenMsgException(Oh.INTERNAL_SERVER_ERROR, e.message!!)
        }
    }
}

