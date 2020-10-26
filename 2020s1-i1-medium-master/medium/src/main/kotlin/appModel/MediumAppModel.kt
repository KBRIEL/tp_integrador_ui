package appModel
import org.eclipse.xtend.lib.annotations.Accessors
import org.ui.*
import org.ui.bootstrap.getMediumSystem


import org.ui.bootstrap.categories
import org.uqbar.commons.model.annotations.Observable

@Accessors
@Observable
class MediumAppModel(mediumSystem: MediumSystem) {
    val medium = mediumSystem
   // var medium: MediumSystem = MediumSystem();
    var idGen :IdGenerator= IdGenerator();
    var authors = medium.authors
    var email: String? = ""
    var password: String? =""


    //private final val idGenerator: org.ui.IdGenerator /* compiled code */

    val notes: MutableList<Note> = medium.notes

    var notesAppModel: MutableList<NoteAppModel> = notes.map{ NoteAppModel(it)}.toMutableList()
    //var selectedNote : NoteAppModel?= notesAppModel.get(0)
   // var id: String = selectedNote!!.id
   // var title : String = selectedNote!!.title
//--------------note------------
    var setCategoria :String = ""
    var setId: String = ""
    var setBody : String = ""
    var setTitle : String = ""


    public fun addComent(noteId: String, authorId: String, comment :String) {

    medium.addComment(noteId, authorId, DraftComment(comment))

    }
    public open fun addNote(authorId: String, draftNote: org.ui.DraftNote){
        print(authorId + "  " +  draftNote.title)
       medium.addNote(authorId, draftNote)

    }


    public open fun editNote(noteId: String, draftNote: org.ui.DraftNote){
        print(noteId)
        medium.editNote(noteId, draftNote)
    }

    //private final fun existNoteWithSameTitle(authorId: kotlin.String, title: kotlin.String): kotlin.Unit { /* compiled code */ }



    //private final fun existUserWithEmail(email: kotlin.String): kotlin.Unit { /* compiled code */ }

   // private final fun formatCategories(categories: kotlin.String): kotlin.collections.List<kotlin.String> { /* compiled code */ }



   fun getAuthor(id :String): org.ui.Author {
        return medium.getAuthor(id)// va el id del autor
    }

   fun getNote(noteId: String): org.ui.Note {
       return medium.getNote(noteId)
   }

    fun latestAddedNotes(): List<org.ui.Note> {
        return medium.latestAddedNotes()
    }

    fun login(email: String, password: String): org.ui.Author {
        return medium.login(email, password)
    }

    fun registerAuthor(name: String, email: String, password: String, photo:String): org.ui.Author {
        return medium.registerAuthor(name, email, password, photo)
    }

    fun removeNote(noteId: String){
        medium.removeNote(noteId)
    }

    fun searchNotesByAuthorId(authorId: String): List<org.ui.Note> {
        return medium.searchNotesByAuthorId(authorId)
    }

    fun searchNotesByCategory(category: String): List<org.ui.Note> {
        return medium.searchNotesByCategory(category)
    }

    fun searchNotesByTitle(title: kotlin.String): List<org.ui.Note> {
        return medium.searchNotesByTitle(title)
    }

    fun ofListToString(note: Note): String{
        var str =""
       for(i in note.categories ){
           str += i + ", "
        }
        return str
    }
    fun setVar(sN : Note){
        setId = sN.id
        setTitle = sN.title
        setBody = sN.body
        setCategoria = ofListToString(sN!!)
    }
    fun cleanVar(){
        setId=""
        setTitle = ""
        setBody = ""
        setCategoria =""
    }
    fun existId(i:String) : Boolean{
        return notes.any { it.id == i}
    }
    fun actualizarNotas(){
        notesAppModel = notes.map{ NoteAppModel(it)}.toMutableList()
    }

    fun notasXAuthor(idAuthor : String): List<NoteAppModel>{
        return notesAppModel.filter{it.autor.id == idAuthor}
    }
    fun mismoTitulo(title : String, idAuthor : String):Boolean{

        return notesAppModel.any{(it.autor.id == idAuthor && title == it.title) or (it.autor.id == idAuthor && it.title =="")}
    }
    fun notasXAuthor2(idAuthor : String): List<String> {
        var lista: MutableList<String> = mutableListOf()

        for (i in notesAppModel) {
            if (i.autor.id == idAuthor) {
                lista.add(i.autor.name)
                lista.add(i.title)
            }

        }
        return lista
    }


    fun notasXATitle(idA : String):List<String>{
        return notasXAuthor(idA).map{it.title}
    }

    fun notasXAID(idA : String):List<String>{
        return notasXAuthor(idA).map{it.id}
    }
    fun nombresNotas(): List<String>{
        return medium.notes.map{it.title
        }
    }


}
