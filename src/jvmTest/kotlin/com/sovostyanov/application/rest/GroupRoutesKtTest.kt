package com.sovostyanov.application.rest

import com.sovostyanov.application.main
import io.ktor.client.request.*
import io.ktor.server.testing.*
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
}