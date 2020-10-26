package view

import appModel.AuthorAppModel
import appModel.MediumAppModel
import appModel.NoteAppModel
import org.ui.Author
import org.ui.DraftNote
import org.ui.Note
import org.ui.bootstrap.getMediumSystem
import org.uqbar.arena.Application
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class AgregarEditarWindows (owner: WindowOwner, model: MediumAppModel, note : NoteAppModel) : SimpleWindow<MediumAppModel>(owner, model) {
    var noteId = note.id
    var modelo = model
    var autor = note.autor
   //// var notesAuthor = modelo.notasXAuthor(autor.id)




    override fun addActions(p0: Panel?) {

    }

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Medium"
        setMinWidth(220)
        Panel(mainPanel) with {
        Label(it) with {
            text = "Title"
            width = 220
            align("left")
        }
        TextBox(it) with {
            bindTo("setTitle")
            width = 220
        }
        Label(it) with {
            text = "Body"
            width = 220
            align("left")
        }
        TextBox(it) with {
            bindTo("setBody")
            width = 220
        }

        Label(it) with {
            text = "Categories"
            width = 220
            align("left")
        }
        TextBox(it) with {
            bindTo("setCategoria")
            width = 220
        }
        Panel(it) with {
             asHorizontal()
            Button(it) with {
                text = "Accept"
                width = 75
                onClick(Action {
                    if( modelo.existId(modelo.setId)){

                       modelo.editNote(noteId, DraftNote(modelo.setTitle, modelo.setBody, modelo.setCategoria))
                            }
                    else{

                            modelo.addNote(autor.id, DraftNote(modelo.setTitle, modelo.setBody, modelo.setCategoria))


                    }
                    abrirVentana()

                })
            }
            Button(it) with {
                text = "Cancel"
                width = 75
                onClick(Action {
                    close()
                    var autorAM : AuthorAppModel= AuthorAppModel(autor , modelo.medium)
                    AuthorWindow(this@AgregarEditarWindows, autorAM,modelo.medium).open()



                })
            }
        }


    }

    }
    fun abrirVentana(){
        close()
        modelo.actualizarNotas()
        var autorAM : AuthorAppModel = AuthorAppModel(autor , modelo.medium)
        AuthorWindow(this@AgregarEditarWindows, autorAM , modelo.medium).open()

    }
}


class MyApp4() : Application() {
val medium = getMediumSystem()
    var note : NoteAppModel = NoteAppModel(medium.getNote("n_1"))
    override fun createMainWindow(): Window<*> {
       return AgregarEditarWindows(this, MediumAppModel(  getMediumSystem()),note)
    }

}



fun main() = MyApp4().start()