package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.member.id.PublicId

open class Member(
    // 고유식별자
    var publicId: PublicId,

    // 권한
    var roles: List<Role>,
)
