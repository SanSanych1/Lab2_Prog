package com.sovostyanov.application.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import com.sovostyanov.application.repo.studentsRepo

fun Route.groupRoutes() {
    route(Config.groupsPath) {
        get {
            val groups = studentsRepo.read().map { it.elem.group }.toSet()
            if (groups.isEmpty()) {
                call.respondText("No groups found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(groups)
            }
        }
        get ("{id}"){
            val id =
                call.parameters["id"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
            val students = studentsRepo.read().filter { it.elem.group == id }
            if (students.isEmpty()) {
                call.respondText("No students found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(students)
            }
        }
    }
}