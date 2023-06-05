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
import com.sovostyanov.application.data.Lesson
import com.sovostyanov.application.data.Student
import component.student.CStudentLessons
import js.core.get
import query.QueryError
import react.router.useParams
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQuery
import tanstack.react.query.useQueryClient
import tools.HTTPResult
import tools.fetch
import tools.fetchText


val studentLessonsContainer = FC("StudentLessonsContainer") { _: Props ->
    val queryClient = useQueryClient()
    val studentId = useParams()["id"]
    val studentListQueryKey = arrayOf("studentLessons").unsafeCast<QueryKey>()

    val query =
            useQuery<String, QueryError, String, QueryKey>(
                queryKey = studentListQueryKey,
                queryFn = {
                    fetchText("${Config.studentsPath}${studentId}/lessons/")
                }
            )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val items = Json.decodeFromString<Array<Item<Lesson>>>(query.data ?: "")

        CStudentLessons {
            lessons = items
        }
    }
}