package com.freshtuna.tooth.security.userDetail

import com.freshtuna.tooth.member.Member
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class ToothUserDetail(
    val member: Member
) : UserDetails {

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return member.roles
            .map { role -> SimpleGrantedAuthority(role.name) }
    }

    override fun getPassword(): String {
        return ""
    }

    override fun getUsername(): String {
        return member.id.stringID()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
