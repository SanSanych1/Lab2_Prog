import com.sovostyanov.application.config.Config
import component.CMenu
import component.group.item.groupContainer
import component.menuContainer
import component.student.item.studentContainer
import component.student.list.studentsContainer
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import tanstack.query.core.QueryClient
import tanstack.react.query.QueryClientProvider
import web.dom.document

fun main() {
    val container = document.getElementById("root")!!
    createRoot(container).render(app.create())
}

val app = FC<Props> ("App"){
    HashRouter{
        QueryClientProvider {
            client = QueryClient()
            menuContainer()
            Routes{
                Route{
                    path = Config.studentsPath
                    element = studentsContainer.create{
                        this.searchByGroup = false
                    }
                }
                Route{
                    path = Config.groupsPath
                    element = groupContainer.create{}
                }
                Route{
                    path = Config.studentsPath + ":name"
                    element = studentContainer.create()
                }
                Route{
                    path = Config.groupsPath + ":name"
                    element = studentsContainer.create{
                        this.searchByGroup = true
                    }
                }
            }

        }
    }
}