package com.sovostyanov.application.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.sovostyanov.application.common.ItemId

@Serializable
class Student(
    val firstname: String,
    val surname: String,
    val group: String
){
    fun fullname() =
        "$firstname $surname"
}

typealias StudentId = ItemId

val Student.json
    get() = Json.encodeToString(this)

