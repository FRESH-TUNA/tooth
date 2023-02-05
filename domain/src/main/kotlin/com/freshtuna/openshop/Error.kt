package com.freshtuna.openshop

enum class Error {
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
    INTERNAL_SERVER_ERROR
}
