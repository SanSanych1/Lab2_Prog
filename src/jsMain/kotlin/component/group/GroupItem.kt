package component.group

import react.FC
import react.Props
import react.dom.html.ReactHTML.span
import com.sovostyanov.application.data.Student
import csstype.AlignItems
import csstype.Display
import csstype.Margin
import csstype.px
import emotion.react.css
import react.dom.html.ReactHTML.div

external interface GroupItemProps : Props {
    var group: String
}

val CGroupItem = FC<GroupItemProps>("GroupItem") { props ->
    +props.group
}