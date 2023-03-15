순수해야하는 것 -> 도메인

그럼 도메인은 무엇인가?

유저 <-> Adapter <-> 도메인(중심) <-> Adapter <-> DB

Adapter -> 도메인: incoming port
도메인 -> Adapter: outgoing port

incoming port 구현체 -> Service
outgoing port 구현체 -> Adapter


## 확장함수 예제
```kotlin
package com.freshtuna.openshop.config

import com.freshtuna.openshop.member.LocalMember

class MemberEntity(localMember: LocalMember) {
    fun te(localMember: LocalMember) {
        val str = "esfsfEFEsfsf dfsfs"
        str.toLowercaseAndRemoveSpace()
    }
}

fun LocalMember.toEntity() {

}

fun String.toLowercaseAndRemoveSpace() = this.lowercase().replace(" ", ""
```
