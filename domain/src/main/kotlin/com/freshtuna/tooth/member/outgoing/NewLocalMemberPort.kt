package com.freshtuna.tooth.member.outgoing

import com.freshtuna.tooth.member.command.SignUpCommand
import com.freshtuna.tooth.member.LocalMember

interface NewLocalMemberPort {
    /**
     * 대이터 엑세스 계층에 새로운 로컬벰버 저장을 요청한다.
     * 저장이 성공하면 true를 반환한다.
     */
    fun new(command: SignUpCommand) : LocalMember
}
