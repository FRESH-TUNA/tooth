package com.freshtuna.openshop.exception.constant

import com.freshtuna.openshop.exception.OpenMsgException

enum class Oh {
    /**
     * LocalMember
     */
    LOCAL_MEMBER_NOT_FOUNDED,
    PASSWORD_NOT_MATCHED,

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
    }
}
