package component.student.list

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

external interface StudentInListProps : Props {
    var student: Student
}

val CStudentInList = FC<StudentInListProps>("StudentInList") { props ->

    span {
        css {
            margin = Margin(0.px, 5.px)
        }
        +props.student.firstname
    }
    span {
        css {
            margin = Margin(0.px, 5.px)
        }
        +props.student.surname
    }
    span {
        css {
            margin = Margin(0.px, 5.px)
        }
        +props.student.group

    }
}