package view

import appModel.AuthorAppModel
import appModel.MediumAppModel
import appModel.NoteAppModel


import org.ui.MediumSystem
import org.uqbar.arena.kotlin.extensions.*


import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow

import org.uqbar.arena.windows.WindowOwner
import java.awt.Color


class AuthorWindow(owner: WindowOwner, model: AuthorAppModel, mediumSystem: MediumSystem) : SimpleWindow<AuthorAppModel>(owner, model)  {

    val mediumAM = MediumAppModel(mediumSystem)
    var modelo = model





    override fun addActions(p0: Panel?) {
    }

    override fun createFormPanel(mainPanel: Panel) {

        title = "Author view "
        Panel(mainPanel) with {


            Panel(it) with {



                Label(it) with {
                    text = ""

                    color = Color.BLACK
                    align("center")

                }

            }

           Panel(it) with {



               table<NoteAppModel>(it) {

                    bindItemsTo("notesAppModelA")// lista de notas del autor
                    bindSelectionTo("selectedNote")// nota elegida

                    width=500
                    visibleRows = 10
                    column {
                        title = "#"
                        fixedSize = 70
                        bindContentsTo("id")
                        align("center")

                    }
                    column {
                        title = "title"
                        fixedSize = 500
                        align("left")
                        bindContentsTo("title")
                    }

                }
            }

            Panel(it) with {
                width = 2000

                asHorizontal()

                Button(it) with {
                    text = "Add New Note"
                    width = 100
                    onClick {
                        addNote()

                    }

                    }

                    Panel(it) with {
                        width = 2000
                        Button(it) with {
                            text = "Edit Note"
                            width = 100
                            onClick {
                                editNote()


                            }
                        }
                    }
                        Panel(it) with {
                        Button(it) with {
                            text = "Delete Note"
                            width = 100

                            onClick {
                                deleteNote()

                            }
                        }
                    }
                    Label(it) with {
                        text = ""
                        width = 200
                    }
                }
            }

        }

    fun irEdit(){

        AgregarEditarWindows(this, mediumAM, modelo.selectedNote!!).open()
    }

    private fun addNote() {
        this.close()
        mediumAM.cleanVar()
        AgregarEditarWindows(owner, mediumAM, modelo.selectedNote!!).open()


    }

    private fun editNote() {
        this.close()
        mediumAM.setVar(modelo.selectedNote!!.note)
        AgregarEditarWindows(owner,  mediumAM, modelo.selectedNote!!).open()

    }

    private fun deleteNote(){
        this.close()
        EliminacionWindows(owner,  mediumAM,modelo.selectedNote!!.note).open()
    }




}

