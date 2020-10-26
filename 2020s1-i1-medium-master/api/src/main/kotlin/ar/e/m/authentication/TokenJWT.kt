package ar.e.m.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm

import javalinjwt.JWTGenerator
import javalinjwt.JWTProvider
import org.ui.Author

class AuthorGenerator: JWTGenerator<Author> {

    override fun generate(author: Author, algorithm: Algorithm): String {
        val token: JWTCreator.Builder = JWT.create().withClaim("id", author.id)
        return token.sign(algorithm)
    }
}

class TokenJWT {

    val algorithm = Algorithm.HMAC256("very_secret")
    val generator = AuthorGenerator()
    val verifier = JWT.require(algorithm).build()
    val provider = JWTProvider(algorithm, generator, verifier)

    fun generateToken(author: Author): String {
        return provider.generateToken(author)
    }

    fun validate(token: String): String {
        val token = provider.validateToken(token)
        if(!token.isPresent) throw NotFoundToken()
        return token.get().getClaim("id").asString()
    }

}

class NotFoundToken : Exception()

