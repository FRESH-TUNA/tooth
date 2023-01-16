package com.freshtuna.openshop

class LocalMember(
    id: String,
    name: String,
    nickname: String,
    roles: List<Role>,
    var localId: String,
    var password: String
) : Member(id, name, nickname, roles) {

}
