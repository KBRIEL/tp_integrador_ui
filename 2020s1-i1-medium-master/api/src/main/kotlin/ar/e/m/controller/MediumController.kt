package ar.e.m.controller

import ar.e.m.authentication.TokenJWT
import ar.e.m.exception.NotFoundContent
import ar.e.m.exception.NotFoundUser
import ar.e.m.mapper.*
import io.javalin.http.Context
import org.ui.*
import org.ui.Comment

class MediumController(system: MediumSystem, tokenJWT: TokenJWT) {
    var medium = system
    var tokenJWT = tokenJWT
    var authorT =""
    var user : Author? = null
    var idUser: String ="a_1"
    var idGen : IdGenerator = IdGenerator()




//----------------------------------------------------------------------------------------------------------------------

    fun getAuthor(ctx:Context) {


        try {
        ctx.header("Authorization", authorT)
        ctx.status(200)
        ctx.json(mapOf(
                "name" to  user!!.name,
                "email" to user!!.email,
                "password" to user!!.password,
                "photo" to  user!!.photo,
                "result" to "Ok"
        ))
    } catch (e: NotFound)
    { ctx.status(404)
        ctx.json(mapOf(
                "result" to "error",
                "message" to "Author not found"
        ))


    }


    }


//----------------------------------------------------------------------------------------------------------------------

    fun getNotesAuthor(ctx:Context){


        var notes : MutableList<NoteMapper> = medium.notes.map{ NoteMapper(it) }.toMutableList()
        var authorNotes : MutableList<NoteMapper> = notes.filter { it.author == user }.toMutableList()



        try {

            ctx.status(200)
            ctx.json(mapOf(
                    "lastNotes" to authorNotes
            ))


        }  catch (e: NotFoundContent) {
            ctx.status(404)
            ctx.json({
                "result" to "error"
                "message" to "note not found"
            }
            )
        }



    }
//----------------------------------------------------------------------------------------------------------------------

    fun getLastNotes(ctx:Context){

        var revert : List<Note> = medium.notes.reversed()
        var notes : MutableList<NoteMapper> = revert.map{ NoteMapper(it) }.toMutableList()



        try {

            ctx.status(200)
            ctx.json(sixNotes(notes))


        }  catch (e: NotFoundContent) {
            ctx.status(404)
            ctx.json({
                "result" to "error"
                "message" to "note not found"
            }
            )
        }



    }

    fun sixNotes(list : List<NoteMapper>):List<NoteMapper>{
        var lista : MutableList<NoteMapper> = mutableListOf()
        for( i in 0..5){
            lista.add(list.get(i))
        }
        return lista

    }

//----------------------------------------------------------------------------------------------------------------------

    fun getNoteWithId(ctx:Context){//get
        val noteId = ctx.pathParam("Id")
        var notes : MutableList<NoteMapper> = medium.notes.map{ NoteMapper(it) }.toMutableList()
        var note : NoteMapper = notes.filter {it.id ==  noteId }.get(0)




        try {

            ctx.status(200)
            ctx.json(note)


        }  catch (e: NotFoundContent) {
            ctx.status(404)
            ctx.json({
                "result" to "error"
                "message" to "note not found"
            }
            )
        }



    }



//----------------------------------------------------------------------------------------------------------------------

    fun addComment(ctx:Context){//post
        val noteId = ctx.pathParam(":Id")
        val preComment = ctx.bodyAsClass(AddComment::class.java)
        var note :Note = medium.notes.filter{it.id == noteId }.get(0)
        var comment = Comment(idGen.nextCommentId(), user!!,preComment.body)
        note.comments.add(comment)
        try{
        ctx.status(200)
        ctx.json(mapOf(
                "body" to comment.body

        ))}
    catch (e: NotFoundContent) {
        ctx.status(404)
        ctx.json(mapOf(
                "result" to "error",
                "message" to "Content not found"
        ))
    }
}





//----------------------------------------------------------------------------------------------------------------------
    fun search(ctx: Context){
        val searchTitle:String?  = ctx.queryParam("text")
    println(searchTitle)

        var result: MutableList<NoteMapper>  = searchingNotes( searchTitle)

        ctx.status(200)
        ctx.json(mapOf(
                "content" to result

        ))
    }

    private fun searchingNotes( searchTitle: String?): MutableList<NoteMapper> {
    val notesMapper = medium.notes.filter { it.title.contains(searchTitle!!) }.map { NoteMapper (it) }.toMutableList()
    return notesMapper
}
//----------------------------------------------------------------------------------------------------------------------
    fun searchNotesByCategory(ctx:Context) {
    val category = ctx.pathParam(":name")
    var notesByCategory : List<Note> = medium.notes.filter{it.categories.contains(category) }
    var result : List<NoteMapper> = notesByCategory.map{NoteMapper(it)}

    try {

        ctx.status(200)
        ctx.json(result)


    }  catch (e: NotFoundContent) {
        ctx.status(404)
        ctx.json({
            "result" to "error"
            "message" to "notes not found"
        }
        )
    }


    }



//----------------------------------------------------------------------------------------------------------------------
fun getUserById(userId: String): Author {
    return medium.authors.find { it.id == userId } ?: throw NotFoundUser()
}


//----------------------------------------------------------------------------------------------------------------------



    fun setAuthor(author: Author) {
        user=author
    }


}


