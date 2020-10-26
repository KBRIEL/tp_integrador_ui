package appModel

import org.eclipse.xtend.lib.annotations.Accessors
import org.ui.Author
import org.ui.MediumSystem
import org.ui.Note
import org.ui.bootstrap.getMediumSystem
import org.uqbar.commons.model.annotations.Observable

@Accessors
@Observable
class AuthorAppModel(autor: Author,mediumSystem: MediumSystem) {

    var medium =mediumSystem
    var mediumAM = MediumAppModel(medium)
    val email: String = autor.email
    var autor= autor

    //val id: String =  autor.id

   val name: String =  autor.name

   var password: kotlin.String =  autor.password

    var photo: kotlin.String =  autor.photo
    //var authorNotes : List<Note> = medium.notes.filter { it.author.id == id  }
    var notesAppModelA : List<NoteAppModel> = mediumAM.notasXAuthor(autor.id)
   // var notesAppModel : List<NoteAppModel> = authorNotes.map { NoteAppModel(it) }

    var selectedNote : NoteAppModel?= notesAppModelA.get(0)

}