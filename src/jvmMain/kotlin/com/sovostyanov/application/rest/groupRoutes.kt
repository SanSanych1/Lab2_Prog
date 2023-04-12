package com.sovostyanov.application.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import com.sovostyanov.application.repo.ListRepo
import com.sovostyanov.application.repo.studentsRepo

fun Route.groupRoutes() {
    route(Config.groupsPath) {
        // Получение всех групп
        get {
            val id = call.request.queryParameters["group"]

            val respondGroups: Set<String>
            val respondStudentsByGroup: ListRepo<Student>

            if (id == null || id.isEmpty())
                call.respond(studentsRepo.read().map { it.elem.group }.toSet())
            else
                call.respond(studentsRepo.read().filter { it.elem.group == id })
        }

//        Получение списка студентов по URL
        get ("{group}"){
            val group =
                call.parameters["group"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
            val students = studentsRepo.read().filter { it.elem.group == group }
            print(students)
            if (students.isEmpty()) {
                call.respondText("No students found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(students)
            }
        }

//        Получение списка студентов
        put {
            val id = call.receive<String>()
            if (id.isEmpty())
                return@put call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
            val students = studentsRepo.read().filter { it.elem.group == id.substring(1, id.length-1) }
            if (students.isEmpty()) {
                call.respondText("No students found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(students)
            }
        }
    }
}