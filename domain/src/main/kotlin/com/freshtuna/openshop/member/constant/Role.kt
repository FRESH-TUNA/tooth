package com.freshtuna.openshop.member.constant

enum class Role {
    ADMIN,
    USER;

    companion object {
        val OF_NEW_MEMBER = listOf(USER)
    }
}
