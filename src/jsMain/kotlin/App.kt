import com.sovostyanov.application.config.Config
import component.group.CMenu
import component.group.groupContainer
import component.student.studentContainer
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
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
            CMenu()
            Routes{
                Route{
                    path = Config.studentsPath
                    element = studentContainer.create{}
                }
                Route{
                    path = Config.groupsPath
                    element = groupContainer.create{}
                }
            }
        }
    }
}