package com.freshtuna.openshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MemberApiApplication

fun main(args: Array<String>) {
    runApplication<MemberApiApplication>(*args)
}