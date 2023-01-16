package com.freshtuna.openshop

class OAuthMember(
    id: String,
    name: String,
    nickname: String,
    roles: List<Role>,
    var provider: OAuthProvider,
    var oauthId: String
) : Member(id, name, nickname, roles)
