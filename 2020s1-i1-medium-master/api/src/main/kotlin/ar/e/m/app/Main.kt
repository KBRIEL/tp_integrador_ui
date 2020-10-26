package ar.e.m.app
import ar.e.m.authentication.AuthenticationAccessManager
import ar.e.m.authentication.TokenJWT
import ar.e.m.controller.MediumController
import ar.e.m.controller.AuthorController
import io.javalin.Javalin

import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import org.ui.Author
import org.ui.bootstrap.getMediumSystem


enum class Roles: Role {
    ANYONE, USER
}


fun main(args: Array<String>) {
    val system = getMediumSystem()
    val tokenJWT    = TokenJWT()
    val medium   = MediumController(system, tokenJWT)
    val authenticationAccessManager = AuthenticationAccessManager(tokenJWT, medium)
    val AuthorController   = AuthorController(medium, tokenJWT)





    val app = Javalin.create {
        it.defaultContentType = "application/json"
        it.registerPlugin(RouteOverviewPlugin("/routes"))
        it.accessManager(authenticationAccessManager)
        it.enableCorsForAllOrigins()
    }.start(7000)

    app.routes {
        path("register") {// token
            post(AuthorController::registerAuthor, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("login") {//token
            post(AuthorController::login, mutableSetOf<Role>(Roles.ANYONE))
        }
        path("user") {// hacer info del usuario con token
                get(medium::getAuthor, mutableSetOf<Role>(Roles.USER))
        }
        path("user") {// hacer notas del usuario con token
            path(":Id") {
                get(medium::getNotesAuthor, mutableSetOf<Role>(Roles.USER))
            }
        }
        path("content") {//hacer ultimas notas cargadas

                get(medium::getLastNotes, mutableSetOf<Role>(Roles.USER))
        }
        path("content") {
            path(":Id") {// retorna la nota con ese id
                get(medium::getNoteWithId, mutableSetOf<Role>(Roles.USER))
            }
        }
        path("content") {
            path(":Id") {// agrega un comentario con token
                post(medium::addComment, mutableSetOf<Role>(Roles.USER))
            }
        }

        path("search") {// retorna una lista de notas que coincidan

            get(medium::search, mutableSetOf<Role>(Roles.USER))

        }

        path("category") {// retorna  una lista de notas segun la categoria
            path(":name") {
                get(medium::searchNotesByCategory, mutableSetOf<Role>(Roles.USER))
            }
        }


    }
}


