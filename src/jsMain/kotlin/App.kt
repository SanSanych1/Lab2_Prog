import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Lesson
import com.sovostyanov.application.data.Student
import component.lesson.CLessonAdd
import component.lesson.CLessonEditContainer
import component.lesson.CLessonInList
import component.menuContainer
import component.student.CStudentAdd
import component.student.CStudentEdit
import component.student.item.CStudentInList
import component.template.RestContainerChildProps
import component.template.restContainer
import component.template.restList
import react.FC
import react.Props
import react.create
import react.createContext
import react.dom.client.createRoot
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import tanstack.query.core.QueryClient
import tanstack.query.core.QueryKey
import tanstack.react.query.QueryClientProvider
import tanstack.react.query.devtools.ReactQueryDevtools
import web.dom.document

val invalidateRepoKey = createContext<QueryKey>()

fun main() {
    val container = document.getElementById("root")!!
    createRoot(container).render(app.create())
}

val app = FC<Props> ("App"){
    HashRouter{
        QueryClientProvider {
            client = QueryClient()
            menuContainer()
            Routes {
                Route {
                    path = Config.studentsPath
                    val list: FC<RestContainerChildProps<Student>> =
                        restList(
                            CStudentInList,
                            CStudentAdd,
                            CStudentEdit
                        )
                    element = restContainer(
                        Config.studentsPath,
                        list,
                        "students"
                    ).create()
                }
                Route {
                    path = Config.lessonsPath
                    val list: FC<RestContainerChildProps<Lesson>> =
                        restList(
                            CLessonInList,
                            CLessonAdd,
                            CLessonEditContainer
                        )
                    element = restContainer(
                        Config.lessonsPath,
                        list,
                        "lessons"
                    ).create()
                }
                Route {
                    path = Config.groupsPath

                }
            }
        }
    }
}