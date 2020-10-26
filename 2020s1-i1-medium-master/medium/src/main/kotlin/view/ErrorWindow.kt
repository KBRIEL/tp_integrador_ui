package view


import appModel.MediumAppModel

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action


class ErrorWindow (owner: WindowOwner, model: MediumAppModel) : SimpleWindow<MediumAppModel>(owner, model)  {

    var modelo : MediumAppModel = model
   // var texto : String = error

    override fun createFormPanel(mainPanel: Panel?) {
        Panel(mainPanel) with {
            Label(it) with {
                text = " Email o Password incorrecto "
                width = 300
                align("left")
            }



            Panel(it) with {
                asHorizontal()
                Panel(it) with {

                    width = 200
                    Label(it) with {
                        text = " Intentelo Nuevamente "
                        width = 200
                        align("center")
                    }
                }
                Button(it) with {

                    text = "Accept"
                    width = 75

                    onClick(Action {


                        close()


                    })
                }

                }
            }

        }

    override fun addActions(p0: Panel?) {

    }
}