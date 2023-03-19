package component

import com.sovostyanov.application.common.Item
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Student
import component.student.list.QueryError
import component.student.list.StudentsContainerProps
import csstype.*
import emotion.react.css
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.router.dom.Link
import tanstack.query.core.QueryKey
import tanstack.react.query.useQuery
import tools.fetchText

external interface MenuProps : Props {
    var menuItems: Array<String>
}

val CMenu = FC<MenuProps>("Menu") { props ->
    div{
        css {
            display = Display.flex
            background = rgb(255, 255, 255)
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
            margin = Margin(10.px, Auto.auto)
        }
        props.menuItems.forEach {
            Link{
                css {
                    display = Display.block
                    textDecoration = None.none
                    border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                    borderRadius = 35.px;
                    minWidth = (1/3).pct
                    margin = Margin(0.px,10.px)
                    padding = 10.px
                }
                +"${it[0].uppercase()}${it.substring(1, it.length)}"
                to = it
            }
        }
    }
}

val menuContainer = FC("MenuContainer") { _:Props ->
    val studentListQueryKey = arrayOf("menu").unsafeCast<QueryKey>()

    val query = useQuery<String, QueryError, String, QueryKey>(
                    queryKey = studentListQueryKey,
                    queryFn = {
                        fetchText(Config.menuPath)
                    }
                )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val items = Json.decodeFromString<Array<String>>(query.data ?: "")
        CMenu{
            this.menuItems = items
        }
    }
}