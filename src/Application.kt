package com.example

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalDateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            //Enable support for java.time.*
            registerModule(JavaTimeModule())

            //Serialization Features
            enable(SerializationFeature.INDENT_OUTPUT)
            //enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            //enable(SerializationFeature.WRAP_ROOT_VALUE)
            enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/ship") {
            call.respond(SpaceShip(null, 80, listOf("Mike")))
        }
    }
}

data class SpaceShip(
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    val name: String?,
    val fuel: Int,
    val crew: List<String>,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    val launchDate: LocalDateTime = LocalDateTime.now()

)
