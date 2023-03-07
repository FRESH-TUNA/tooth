package com.freshtuna.openshop.exception.constant

import com.freshtuna.openshop.exception.OpenException
import com.freshtuna.openshop.exception.OpenMsgException

enum class Oh {
    /**
     * LocalMember
     */
    LOCAL_MEMBER_NOT_FOUNDED,
    LOCAL_AUTHENTICATION_FAIL,
    BREAK_PASSWORD_RULE,

    /**
     * JWT
     */
    JWT_ERROR,

    /**
     * internal server error
     */
    INTERNAL_SERVER_ERROR;

    companion object {
        fun localMemberNotExisted(localId: String): Nothing {
            throw OpenMsgException(LOCAL_MEMBER_NOT_FOUNDED, "아이디: $localId 와 일치하는 계정이 없습니다.")
        }

        fun localIdUsed(localId: String): Nothing {
            throw OpenMsgException(LOCAL_MEMBER_NOT_FOUNDED, "아이디: $localId 는 이미 사용중입니다.")
        }

        fun breakPasswordRule(): Nothing {
            throw OpenException(BREAK_PASSWORD_RULE)
        }

        fun localAuthenticationFail(): Nothing {
            throw OpenMsgException(LOCAL_AUTHENTICATION_FAIL, "아이디나 패스워드가 일치하지 않습니다")
        }
    }
}
