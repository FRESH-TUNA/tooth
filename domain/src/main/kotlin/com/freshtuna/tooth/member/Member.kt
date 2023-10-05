package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.ID

open class Member(
    // 고유식별자 (pk)
    var id: ID,

    // 공개식별자
    var publicID: ID,

    // 권한
    var roles: List<Role>,
)
