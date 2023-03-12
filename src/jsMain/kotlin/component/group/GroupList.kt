package component.group

import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import csstype.*
import emotion.react.css
import react.router.dom.Link

external interface QueryError

external interface GroupListProps : Props {
    var groups: Array<String>
}

val CGroupList = FC<GroupListProps>("GroupList") { props ->
    ol {
        css {
            display = Display.flex
            boxShadow = BoxShadow(0.px, 0.px, 6.px, rgba(32, 33, 36, 0.28))
            background = rgb(255, 255, 255);
            borderRadius = 35.px;
            padding = 10.px
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
            listStyleType = None.none
            flexWrap = FlexWrap.wrap
            maxWidth = 40.pct
            minWidth = 450.px
            margin = Auto.auto
        }
        props.groups.forEach { groupItem ->
            Link {
                css {
                    display = Display.block
                    textDecoration = None.none
                    padding = 0.px
                    margin = 5.px
                }


                li {
                    css {
                        width = 70.px
                        height = 70.px
                        border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                        background = rgb(255, 255, 255);
                        borderRadius = 100.pct;
                        lineHeight = 70.px
                        textAlign = TextAlign.center
                    }
                    CGroupItem {
                        group = groupItem
                    }
                }
            }
        }
    }
}
