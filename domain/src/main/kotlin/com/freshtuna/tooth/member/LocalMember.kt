package com.freshtuna.tooth.member

import com.freshtuna.tooth.id.ID
import com.freshtuna.tooth.member.constant.Role

class LocalMember(
    id: ID,
    publicID: ID,
    roles: List<Role>,

    var localID: ID,
    var password: Password
) : Member(id, publicID, roles) {

    fun changePassword(newPW: Password) {
        password = newPW
    }
}
