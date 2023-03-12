package component.student

import js.core.jso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import com.sovostyanov.application.common.Item
import com.sovostyanov.application.common.ItemId
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import js.core.Record
import js.core.get
import react.router.useParams
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQuery
import tanstack.react.query.useQueryClient
import tools.HTTPResult
import tools.fetch
import tools.fetchText
import kotlin.js.json

val studentContainer = FC("StudentContainer") { _: Props ->
    val queryClient = useQueryClient()
    val studentId = useParams()["name"]

    val studentQueryKey = arrayOf("student").unsafeCast<QueryKey>()

    val query = useQuery<String, QueryError, String, QueryKey>(
        queryKey = studentQueryKey,
        queryFn = {
            fetchText(Config.studentsPath + studentId)
        }
    )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val item = Json.decodeFromString<Item<Student>>(query.data ?: "")

        CStudent {
            student = item
        }
    }
}