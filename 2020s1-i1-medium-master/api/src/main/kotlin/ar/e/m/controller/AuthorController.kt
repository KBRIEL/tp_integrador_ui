package ar.e.m.controller

import ar.e.m.authentication.TokenJWT
import ar.e.m.mapper.LoginAuthor
import ar.e.m.mapper.RegisterAuthor
import io.javalin.http.Context
import org.ui.Author
import org.ui.NotFound


class AuthorController(medium: MediumController, tokenJWT: TokenJWT) {
    var mediumC : MediumController = medium
    var medium = medium.medium
    var tokenJWT = tokenJWT
    var authorT =""
    var user : Author? = null


    fun registerAuthor(ctx: Context) {
        try {
            val registerAuthor = ctx.bodyValidator<RegisterAuthor>()
                    .check({
                        it.name != null && it.email != null && it.password != null && it.photo != null
                    }, "Invalid body: name, email, password, and photo should not be null")
                    .get()
            val author = registerAnAuthor(registerAuthor.name, registerAuthor.email, registerAuthor.password, registerAuthor.photo)
            mediumC.setAuthor(author)
            ctx.status(201)
            ctx.json(mapOf(
                    "name" to author.name,
                    "id" to author.id,
                    "email" to author.email,
                    "photo" to author.photo,
                    "result" to "Ok"
            ))
        } catch (e: Exception) {
            ctx.status(400)
            ctx.json(mapOf(
                    "message" to e.message.toString()
            ))

        }

    }
    fun registerAnAuthor(name: String, email: String, password: String, photo: String): Author {
        return medium.registerAuthor(name,email,password,photo)

    }

    //----------------------------------------------------------------------------------------------------------------

    fun login(ctx: Context) {

        val loginAuthor = ctx.bodyAsClass(LoginAuthor::class.java)
        try {
            user= medium.login(loginAuthor.email, loginAuthor.password)
            authorT =tokenJWT.generateToken(user!!)
            mediumC.setAuthor(user!!)
            ctx.header("Authorization", authorT)
            ctx.status(200)
            ctx.json(mapOf(
                    "name" to user!!.name,

                    "email" to user!!.email,

                    "result" to "Ok"
            ))
        } catch (e: NotFound) {
            ctx.status(404)
            ctx.json(mapOf(
                    "result" to "error",
                    "message" to "Author not found"
            ))

        }
    }



}
