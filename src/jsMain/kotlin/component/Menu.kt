package component

import com.sovostyanov.application.config.Config
import component.student.QueryError
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

val CMenu = FC("Menu") { _: Props ->
    div{
        css {
            display = Display.flex
            background = rgb(255, 255, 255)
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
            margin = Margin(10.px, Auto.auto)
        }
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
            +"Students"
            to = Config.studentsPath
        }
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
            +"Groups"
            to = Config.groupsPath
        }
    }
}