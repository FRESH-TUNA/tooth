package com.freshtuna.tooth.member

import com.freshtuna.tooth.member.constant.Provider
import com.freshtuna.tooth.member.constant.Role
import com.freshtuna.tooth.id.ID

class OAuthMember(
    id: ID,
    publicID: ID,
    roles: List<Role>,

    var provider: Provider,
    var oauthId: String
) : Member(id, publicID, roles)
