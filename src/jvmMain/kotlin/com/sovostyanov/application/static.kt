package com.sovostyanov.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun HTML.index() {
    head {
        title("WebApp")
        link {
            rel = "icon"
            href = "data:,"
        }
        link {
            href =
                "https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap"
            rel = "stylesheet"
        }
    }
    body {
        style = "font-family: 'Montserrat', sans-serif;"
        div {
            id = "root"
            +"React will be here!!"
        }
        script(src = "/static/Lab2_Prog.js") {}
    }
}

fun Application.static() {
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK, HTML::index)
        }
        static("/static") {
            resources()
        }
    }
}
