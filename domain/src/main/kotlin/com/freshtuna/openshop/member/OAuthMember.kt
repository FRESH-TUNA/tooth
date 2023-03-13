package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Provider
import com.freshtuna.openshop.member.constant.Role
import com.freshtuna.openshop.id.PublicId

class OAuthMember(
    id: PublicId,
    roles: List<Role>,

    var provider: Provider,
    var oauthId: String
) : Member(id, roles)
