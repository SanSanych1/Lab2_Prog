//package component.student.item
//
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.json.Json
//import react.FC
//import react.Props
//import react.dom.html.ReactHTML.div
//import com.sovostyanov.application.common.Item
//import com.sovostyanov.application.common.ItemId
//import com.sovostyanov.application.config.Config
//import com.sovostyanov.application.data.Student
//import component.student.list.QueryError
//import js.core.get
//import js.core.jso
//import kotlinx.serialization.encodeToString
//import react.router.useParams
//import tanstack.query.core.QueryKey
//import tanstack.react.query.useMutation
//import tanstack.react.query.useQuery
//import tanstack.react.query.useQueryClient
//import tools.HTTPResult
//import tools.fetch
//import tools.fetchText
//import kotlin.js.json
//
//val studentContainer = FC("StudentContainer") { _: Props ->
//    val queryClient = useQueryClient()
//    val studentId = useParams()["name"]
//
//    val studentQueryKey = arrayOf("student").unsafeCast<QueryKey>()
//
//    val query = useQuery<String, QueryError, String, QueryKey>(
//        queryKey = studentQueryKey,
//        queryFn = {
//            fetchText(Config.studentsPath + studentId)
//        }
//    )
//
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
//                queryClient.invalidateQueries<Any>(studentQueryKey)
//            }
//        }
//    )
//
//    val updateGroupMutation = useMutation<HTTPResult, Any, Pair<String, ItemId>, Any>(
//        mutationFn = { studentItem: Pair<String, ItemId> ->
//            fetch(
//                Config.studentsPath,
//                jso {
//                    method = "PUT"
//                    headers = json("Content-Type" to "application/json")
//                    body = Json.encodeToString(studentItem)
//                }
//            )
//        },
//        options = jso {
//            onSuccess = { _: Any, _: Any, _: Any? ->
//                queryClient.invalidateQueries<Any>(studentQueryKey)
//            }
//        }
//    )
//
//    if (query.isLoading) div { +"Loading .." }
//    else if (query.isError) div { +"Error!" }
//    else {
//        val item = Json.decodeFromString<Item<Student>>(query.data ?: "")
//
//        CStudent {
//            this.student = item
//            this.saveStudent = {
//                updateStudentMutation.mutateAsync(it, null)
//            }
//            this.saveGroup = {
//                updateGroupMutation.mutateAsync(it, null)
//            }
//        }
//    }
//}