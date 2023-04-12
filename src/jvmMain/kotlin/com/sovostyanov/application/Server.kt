package com.sovostyanov.application

import com.sovostyanov.application.repo.createTestData
import com.sovostyanov.application.rest.groupRoutes
import com.sovostyanov.application.rest.lessonRoutes
import com.sovostyanov.application.rest.menuRoutes
import com.sovostyanov.application.rest.studentRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay


fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "127.0.0.1",
        watchPaths = listOf("classes"),
        module = Application::main
    ).start(wait = true)
}

fun Application.main(isTest: Boolean = true) {
    config(isTest)
    static()
    rest()
    if (isTest) logRoute()
}

fun Application.config(isTest: Boolean) {
    install(ContentNegotiation) {
        json()
    }
    if (isTest) {
        createTestData()
        install(createApplicationPlugin("DelayEmulator") {
            onCall {
                delay(0L)
            }
        })
    }
}

fun Application.rest() {
    routing {
        groupRoutes()
        studentRoutes()
        lessonRoutes()
        menuRoutes()
    }
}