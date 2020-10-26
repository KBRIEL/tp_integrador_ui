package ar.e.m.authentication

import ar.e.m.app.Roles
import ar.e.m.controller.MediumController
import ar.e.m.exception.NotFoundUser
import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.ui.Author

class AuthenticationAccessManager(val tokenJWT: TokenJWT,val medium: MediumController)  : AccessManager {


        fun getUser(token: String): Author {
            try {
                val userId = tokenJWT.validate(token)
                return medium.getUserById(userId)
            } catch (e: NotFoundToken) {
                throw UnauthorizedResponse("Token not found")
            } catch (e: NotFoundUser) {
                throw UnauthorizedResponse("Invalid token")
            }
        }

        override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
            val token = ctx.header("Authorization")
            when {
                token == null && roles.contains(Roles.ANYONE) -> handler.handle(ctx)
                token == null -> throw UnauthorizedResponse("Token not found")
                roles.contains(Roles.ANYONE) -> handler.handle(ctx)
                roles.contains(Roles.USER) -> {
                    getUser(token)
                    handler.handle(ctx)
                }
            }
        }
    }


