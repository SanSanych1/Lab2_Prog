package com.sovostyanov.application.rest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import com.sovostyanov.application.repo.studentsRepo

fun Route.studentRoutes() {
    route(Config.studentsPath) {
        get {
            val students = studentsRepo.read()
            if (students.isEmpty()) {
                call.respondText("No students found", status = HttpStatusCode.NotFound)
            } else {
                call.respond(students)
            }
        }
        get("{id}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
            val studentItem =
                studentsRepo.read(id) ?: return@get call.respondText(
                    "No student with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(studentItem)
        }
        post {
            val student = call.receive<Student>()
            studentsRepo.create(student)
            call.respondText(
                "Student stored correctly",
                status = HttpStatusCode.Created
            )
        }
        delete("{id}") {
            val id = call.parameters["id"]
                ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (studentsRepo.delete(id)) {
                call.respondText(
                    "Student removed correctly",
                    status = HttpStatusCode.Accepted
                )
            } else {
                call.respondText(
                    "Not Found",
                    status = HttpStatusCode.NotFound
                )
            }
        }
        put("{id}") {
            val id = call.parameters["id"] ?: return@put call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            studentsRepo.read(id) ?: return@put call.respondText(
                "No student with id $id",
                status = HttpStatusCode.NotFound
            )
            val newStudent = call.receive<Student>()
            studentsRepo.update(id, newStudent)
            call.respondText(
                "Student updates correctly",
                status = HttpStatusCode.Created
            )
        }
    }
}