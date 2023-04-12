package com.sovostyanov.application.rest

import com.sovostyanov.application.common.Item
import com.sovostyanov.application.common.ItemId
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import com.sovostyanov.application.main
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class GroupRoutesKtTest {

    @Test
    fun testGetId() = testApplication {
        application {
            main()
        }
        client.get("/{id}").apply {
            TODO("Please write your test here")
        }
    }


    @Test
    fun testGetStudentsListByGroup() = testApplication {
        application {
            main()
        }
        withClue("groupInURL") {
            val response = client.get(Config.groupsPath + "29m") {
                contentType(ContentType.Application.Json)
            }

            response.status shouldBe HttpStatusCode.OK

            val responseCollection = Json.decodeFromString<List<Item<Student>>>(response.bodyAsText())

            responseCollection.size shouldBe 6
        }

        withClue("groupInParams") {
            val response = client.get(Config.groupsPath + "/?group=29m") {
                contentType(ContentType.Application.Json)
            }

            response.status shouldBe HttpStatusCode.OK

            val responseCollection = Json.decodeFromString<List<Item<Student>>>(response.bodyAsText())

            responseCollection.size shouldBe 6
        }

        withClue("groupInBody") {
            val response = client.put(Config.groupsPath) {
                contentType(ContentType.Application.Json)
                setBody("\"29m\"")
            }

            response.status shouldBe HttpStatusCode.OK

            val responseCollection = Json.decodeFromString<List<Item<Student>>>(response.bodyAsText())

            responseCollection.size shouldBe 6
        }
    }
}