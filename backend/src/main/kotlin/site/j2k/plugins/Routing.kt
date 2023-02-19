package site.j2k.plugins

import freemarker.cache.ClassTemplateLoader
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText(
                getResourceFileContent("files/index.html"),
                ContentType.Text.Html,
                HttpStatusCode.OK
            )
        }

        get("/our-team") {
            call.respondText(
                getResourceFileContent("files/our-team.html"),
                ContentType.Text.Html,
                HttpStatusCode.OK
            )
        }
        
        static("/") {
            resources("files")
        }
    }
}

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
}

fun getResourceFileContent(fileName: String): String {
    val classLoader = Thread.currentThread().contextClassLoader
    val inputStream = classLoader.getResourceAsStream(fileName)
        ?: throw FileNotFoundException("file $fileName not found")
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))

    return bufferedReader.use { it.readText() }
}
