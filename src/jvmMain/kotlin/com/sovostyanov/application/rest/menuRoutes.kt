package com.sovostyanov.application.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.repo.menuList

fun Route.menuRoutes() {
    route(Config.menuPath) {
        get {
            val menu = menuList
            if (menu.isEmpty()) {
                call.respondText("No menu items found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(menu)
            }
        }
    }
}