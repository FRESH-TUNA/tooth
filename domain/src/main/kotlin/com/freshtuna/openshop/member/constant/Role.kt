package com.freshtuna.openshop.member.constant

enum class Role {
    ADMIN,
    USER;

    companion object {

        private val OF_NEW_MEMBER = listOf(USER)
        fun ofNewMember() : List<Role> {
            return OF_NEW_MEMBER
        }
    }
}
