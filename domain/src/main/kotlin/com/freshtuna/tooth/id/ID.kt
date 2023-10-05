package com.freshtuna.tooth.id

import java.util.UUID

class ID(private val value: String) {

    constructor(id: Long) : this(id.toString())

    constructor(id: UUID) : this(id.toString())
    override fun toString() = value

    fun uuid() = UUID.fromString(value)

    fun longID() = value.toLong()

    fun stringID() = value
}
