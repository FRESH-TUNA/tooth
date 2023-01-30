package com.freshtuna.openshop.member.`in`

import com.freshtuna.openshop.Error
import com.freshtuna.openshop.LocalMember
import com.freshtuna.openshop.Member
import com.freshtuna.openshop.OpenException
import com.freshtuna.openshop.member.out.MemberSearchPort
import java.util.Objects

class MemberSignInService(private val memberSearchPort: MemberSearchPort) : MemberSignInUseCase {
    override fun signInLocalMember(localId: String, password: String): Member {
        val member : LocalMember? = memberSearchPort.findLocalMemberBylocalId(localId)

        if (Objects.isNull(member))
            throw OpenException(Error.LOCAL_MEMBER_NOT_FOUNDED)

        member!!.checkPassword(password)

        return member
    }
}
