package ar.e.m.mapper

import org.ui.Author
import org.ui.Comment
import org.ui.Note

class NoteMapper(note: Note) {
    var id : String = note.id
    var title : String = note.title
    var body : String = note.body
    var author : Author = note.author
    var comments: List<Comment>  = note.comments
    var categories : List<String> = note.categories

}
