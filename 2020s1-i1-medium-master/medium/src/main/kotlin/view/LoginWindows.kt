package view


import appModel.AuthorAppModel
import appModel.MediumAppModel
import org.ui.Author
import org.ui.bootstrap.getMediumSystem
import org.uqbar.arena.Application
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.Action

class LoginWindows (owner: WindowOwner, model: MediumAppModel) : SimpleWindow<MediumAppModel>(owner, model) {
        var modelo : MediumAppModel= model

    override fun createFormPanel(mainPanel: Panel?) {
        title = "Medium"
        setMinWidth(220)
        Label(mainPanel) with {
            text = "Email"
            width = 220
            align("left")
        }
        TextBox(mainPanel) with {
            bindTo("email")

            withFilter {event -> event.potentialTextResult.matches(Regex("[A-Z.a-z0-9_@]*"))}
            width = 220
        }
        Label(mainPanel) with {
            text = "Password"
            width = 220
            align("left")
        }
        PasswordField(mainPanel) with {
            bindTo("password")
            withFilter {event -> event.potentialTextResult.matches(Regex("[A-Za-z0-9]*"))}
            width = 220
        }
        Button(mainPanel) with {
            text = "Login"
            width = 220
            onClick(Action {

                try {close()
                    AuthorWindow(owner, AuthorAppModel(modelo.login(modelo.email!!, modelo.password!!),modelo.medium ), modelo.medium).open()

                } catch (e: Exception) {
                    ErrorWindow(this@LoginWindows,modelo).open()
                    LoginWindows(this@LoginWindows,modelo)
                    throw Exception(" no corresponde el email o el password  ")

                }

            })
            }
            }

    override fun addActions(mainPanel: Panel?) {

}
}
