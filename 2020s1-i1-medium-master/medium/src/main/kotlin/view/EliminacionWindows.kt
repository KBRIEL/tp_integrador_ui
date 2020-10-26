package view

import appModel.AuthorAppModel
import appModel.MediumAppModel
import org.ui.Note


import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel

import org.uqbar.arena.windows.SimpleWindow

import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action


class EliminacionWindows(owner: WindowOwner, model: MediumAppModel, nota : Note) : SimpleWindow<MediumAppModel>(owner, model) {


    var modelo : MediumAppModel= model
    var nota: Note = nota
    var titulo : String = nota.title
    var id : String = nota.id

    override fun createFormPanel(mainPanel: Panel?) {
        Panel(mainPanel) with {
            Label(it) with {
                text = "Do you remove " + titulo
                width = 220
                align("left")
            }

            Panel(it) with {
                asHorizontal()
                Button(it) with {
                    text = "Accept"
                    width = 75
                    onClick(Action {

                        modelo.removeNote(id)
                        modelo.actualizarNotas()
                        var autorAM : AuthorAppModel= AuthorAppModel(nota.author , modelo.medium)
                        AuthorWindow(this@EliminacionWindows, autorAM , modelo.medium).open()
                        close()

                    })
                }
                Button(it) with {
                    text = "Cancel"
                    width = 75
                    onClick(Action {
                        var autorAM : AuthorAppModel = AuthorAppModel(nota.author , modelo.medium)
                        AuthorWindow(this@EliminacionWindows, autorAM , modelo.medium).open()
                        close()

                    })
                }
            }

        }
    }
    override fun addActions(p0: Panel?) {

    }
}
