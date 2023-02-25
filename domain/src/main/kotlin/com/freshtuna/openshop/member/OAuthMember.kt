package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.constant.Provider
import com.freshtuna.openshop.member.constant.Role

class OAuthMember(
    id: String,
    nickname: String,
    roles: List<Role>,
    var provider: Provider,
    var oauthId: String
) : Member(id, nickname, roles)
