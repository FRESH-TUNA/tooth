package com.freshtuna.openshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
@SpringBootApplication
class DBMigrationRunner
fun main(args: Array<String>) {
    runApplication<DBMigrationRunner>(*args)
}
