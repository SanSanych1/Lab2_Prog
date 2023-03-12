package component.student

import com.sovostyanov.application.common.Item
import react.FC
import react.Props
import react.dom.html.ReactHTML.span
import com.sovostyanov.application.data.Student
import csstype.*
import emotion.react.css
import kotlinext.js.getOwnPropertyNames
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.router.dom.Link

external interface StudentProps : Props {
    var student: Item<Student>
}

val CStudent = FC<StudentProps>("Student") { props ->
    div {
        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            flexWrap = FlexWrap.wrap
            alignItems = AlignItems.center
            boxShadow = BoxShadow(0.px, 0.px, 6.px, rgba(32, 33, 36, 0.28))
            background = rgb(255, 255, 255);
            borderRadius = 24.px;
            padding = 10.px
            listStyleType = None.none
            flexWrap = FlexWrap.wrap
            maxWidth = 40.pct
            minWidth = 450.px
            margin = Margin(10.px, Auto.auto)
        }
        p {
            css {
                flexBasis = 40.pct
            }
            +"Firstname"
        }
        p {
            css {
                flexBasis = 40.pct
                border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                background = rgb(255, 255, 255);
                borderRadius = 24.px;
                padding = 8.px
                textAlign = TextAlign.center
            }
            +props.student.elem.firstname
        }

        p {
            css {
                flexBasis = 40.pct
            }
            +"Surname"
        }
        p {
            css {
                flexBasis = 40.pct
                border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                background = rgb(255, 255, 255);
                borderRadius = 24.px;
                padding = 8.px
                textAlign = TextAlign.center
            }
            +props.student.elem.surname
        }

        p {
            css {
                flexBasis = 40.pct
            }
            +"Group"
        }
        p {

            css {
                flexBasis = 40.pct
                border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                background = rgb(255, 255, 255);
                borderRadius = 24.px;
                padding = 8.px
                textAlign = TextAlign.center
            }
            +props.student.elem.group


        }
    }
}