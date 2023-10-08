package com.freshtuna.tooth.token

import com.freshtuna.tooth.exception.constant.Oh
import com.freshtuna.tooth.member.Member
import com.freshtuna.tooth.exception.ToothException
import com.freshtuna.tooth.exception.ToothMsgException
import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.outgoing.MemberSearchPort

import com.freshtuna.tooth.token.incoming.ManageTokenUseCase
import io.github.oshai.KotlinLogging
import io.jsonwebtoken.*
import org.springframework.transaction.annotation.Transactional

import java.security.Key
import java.util.*


open class TokenManager(
    private val secret: Key,
    private val expiredMileSeconds: Long,
    private val roleKey: String,
    private val prefix: String,
    private val memberSearchPort: MemberSearchPort
) : ManageTokenUseCase {

    override fun generate(member: Member): AuthToken {
        val roles: String = member.roles.joinToString(separator = ",") { role -> role.name }
        val now: Long = Date().time
        val expiryDate = Date(now + expiredMileSeconds)

        return AuthToken.of(
            prefix + Jwts.builder()
                .setSubject(member.publicID.toString())
                .claim(roleKey, roles)
                .signWith(secret, SignatureAlgorithm.HS512)
                .setExpiration(expiryDate)
                .compact()
        )
    }

    @Transactional
    override fun identify(token: AuthToken): Member {
        return memberSearchPort.findByPublicID(publicIdOf(token))
    }

    /**
     * helpers
     */
    private fun checkToken(key: Key, authToken: AuthToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(authToken))

        } catch (e: RuntimeException) {
            if (Objects.isNull(e.message))
                throw ToothException(Oh.JWT_ERROR)
            throw ToothMsgException(Oh.JWT_ERROR, e.message!!)
        }
    }

    private fun publicIdOf(authToken: AuthToken): ID {
        return ID(Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(tokenStringWithoutPrefix(authToken))
            .body
            .subject)
    }

    private fun claim(secret: Key, authToken: AuthToken, claimKey: String): String {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(tokenStringWithoutPrefix(authToken))
                .body[claimKey].toString()
        } catch (e: Exception) {
            if (Objects.isNull(e.message))
                throw ToothException(Oh.INTERNAL_SERVER_ERROR)
            throw ToothMsgException(Oh.INTERNAL_SERVER_ERROR, e.message!!)
        }
    }

    private fun tokenStringWithoutPrefix(authToken: AuthToken)
        = authToken.tokenString.removePrefix(prefix)
}
