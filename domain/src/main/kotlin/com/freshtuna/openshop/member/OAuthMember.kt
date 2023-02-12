package com.freshtuna.openshop.member

import com.freshtuna.openshop.member.Member
import com.freshtuna.openshop.oauth.constant.OAuthProvider
import com.freshtuna.openshop.member.constant.Role

class OAuthMember(
    id: String,
    name: String,
    nickname: String,
    roles: List<Role>,
    var provider: OAuthProvider,
    var oauthId: String
) : Member(id, name, nickname, roles)
