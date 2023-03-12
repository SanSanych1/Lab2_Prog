package component.group

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
import component.student.QueryError
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQuery
import tanstack.react.query.useQueryClient
import tools.HTTPResult
import tools.fetch
import tools.fetchText
import kotlin.js.json

val groupContainer = FC("GroupsContainer") { _: Props ->
    val groupListQueryKey = arrayOf("groupList").unsafeCast<QueryKey>()

    val query = useQuery<String, QueryError, String, QueryKey>(
        queryKey = groupListQueryKey,
        queryFn = {
            fetchText(Config.groupsPath)
        }
    )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val items = Json.decodeFromString<Array<String>>(query.data ?: "")
        CGroupList {
            groups = items
        }
    }
}