package component.student.list

import js.core.jso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import com.sovostyanov.application.common.Item
import com.sovostyanov.application.common.ItemId
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import js.core.get
import react.router.useParams
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQuery
import tanstack.react.query.useQueryClient
import tools.HTTPResult
import tools.fetch
import tools.fetchText

external interface StudentsContainerProps : Props {
    var searchByGroup: Boolean
}

val studentsContainer = FC<StudentsContainerProps>("StudentsContainer") { props ->
    val queryClient = useQueryClient()
    val groupId = useParams()["name"]
    val studentListQueryKey = arrayOf("studentList").unsafeCast<QueryKey>()

    val query =
        if(!props.searchByGroup)
            useQuery<String, QueryError, String, QueryKey>(
                queryKey = studentListQueryKey,
                queryFn = {
                    fetchText(Config.studentsPath)
                }
            )
        else
            useQuery<String, QueryError, String, QueryKey>(
                queryKey = studentListQueryKey,
                queryFn = {
                    fetchText(Config.groupsPath + groupId)
                }
            )


//    val addStudentMutation = useMutation<HTTPResult, Any, Student, Any>(
//        mutationFn = { student: Student ->
//            fetch(
//                Config.studentsPath,
//                jso {
//                    method = "POST"
//                    headers = json("Content-Type" to "application/json")
//                    body = Json.encodeToString(student)
//                }
//            )
//        },
//        options = jso {
//            onSuccess = { _: Any, _: Any, _: Any? ->
//                queryClient.invalidateQueries<Any>(studentListQueryKey)
//            }
//        }
//    )

    val deleteStudentMutation = useMutation<HTTPResult, Any, ItemId, Any>(
        { studentId: ItemId ->
            fetch(
                "${Config.studentsPath}$studentId",
                jso {
                    method = "DELETE"

                }
            )
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>(studentListQueryKey)
            }
        }
    )

//    val updateStudentMutation = useMutation<HTTPResult, Any, Item<Student>, Any>(
//        mutationFn = { studentItem: Item<Student> ->
//            fetch(
//                "${Config.studentsPath}${studentItem.id}",
//                jso {
//                    method = "PUT"
//                    headers = json("Content-Type" to "application/json")
//                    body = Json.encodeToString(studentItem.elem)
//                }
//            )
//        },
//        options = jso {
//            onSuccess = { _: Any, _: Any, _: Any? ->
//                queryClient.invalidateQueries<Any>(studentListQueryKey)
//            }
//        }
//    )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val items = Json.decodeFromString<Array<Item<Student>>>(query.data ?: "")
//        CAddStudent {
//            addStudent = {
//                addStudentMutation.mutateAsync(it, null)
//            }
//        }
        CStudentList {
            students = items
            deleteStudent = {
                deleteStudentMutation.mutateAsync(it, null)
            }
        }
    }
}