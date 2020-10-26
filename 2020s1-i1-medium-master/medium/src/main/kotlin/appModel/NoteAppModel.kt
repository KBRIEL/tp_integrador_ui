package appModel

import org.eclipse.xtend.lib.annotations.Accessors
import org.ui.Author
import org.ui.Note
import org.uqbar.commons.model.annotations.Observable

@Accessors
@Observable
class NoteAppModel(note:Note) {
    var note: Note= note
    var id: String = note.id
    var title : String = note.title
    var autor : Author = note.author


}
