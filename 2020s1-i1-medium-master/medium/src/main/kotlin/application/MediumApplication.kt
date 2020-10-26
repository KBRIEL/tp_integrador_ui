package view

import appModel.MediumAppModel
import org.ui.bootstrap.getMediumSystem
import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class MediumApplication : Application() {
    var medium = getMediumSystem()

        override fun createMainWindow(): Window<*> {
            return LoginWindows(this, MediumAppModel(medium))
        }
    }

    fun main() {
        MediumApplication().start()
    }
