import com.sovostyanov.application.common.Item
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import component.CMenu
import component.group.groupContainer
import component.student.CStudent
import component.student.studentContainer
import component.student.studentsContainer
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.p
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
            CMenu()
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