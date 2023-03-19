package component.group.item

import react.FC
import react.Props

external interface GroupItemProps : Props {
    var group: String
}

val CGroupItem = FC<GroupItemProps>("GroupItem") { props ->
    +props.group
}