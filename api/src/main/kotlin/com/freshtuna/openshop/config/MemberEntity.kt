package com.freshtuna.openshop.config

import com.freshtuna.openshop.member.LocalMember

class MemberEntity(localMember: LocalMember) {
    fun te(localMember: LocalMember) {
        val str = "esfsfEFEsfsf dfsfs"
        str.toLowercaseAndRemoveSpace()
    }
}

fun LocalMember.toEntity() {

}

fun String.toLowercaseAndRemoveSpace() = this.lowercase().replace(" ", "")