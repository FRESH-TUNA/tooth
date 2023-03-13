package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.id.LocalId
import com.freshtuna.openshop.id.PublicId

class LocalMember(
    id: PublicId,
    roles: List<Role>,

    var localId: LocalId,
    var password: EncryptedPassword
) : Member(id, roles) {

    fun changePassword(newPW: EncryptedPassword) {
        password = newPW
    }
}
