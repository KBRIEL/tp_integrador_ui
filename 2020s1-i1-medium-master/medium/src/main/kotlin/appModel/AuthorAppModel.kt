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



   val name: String =  autor.name

   var password: kotlin.String =  autor.password

    var photo: kotlin.String =  autor.photo

    var notesAppModelA : List<NoteAppModel> = mediumAM.notasXAuthor(autor.id)


    var selectedNote : NoteAppModel?= notesAppModelA.get(0)

}