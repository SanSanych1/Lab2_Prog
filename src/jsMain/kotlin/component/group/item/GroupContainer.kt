package component.group.item

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import com.sovostyanov.application.config.Config
import component.group.list.CGroupList
import component.student.list.QueryError
import tanstack.query.core.QueryKey
import tanstack.react.query.useQuery
import tools.fetchText

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