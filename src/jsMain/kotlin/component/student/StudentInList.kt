package component.student

import react.FC
import react.Props
import react.dom.html.ReactHTML.span
import com.sovostyanov.application.data.Student

external interface StudentInListProps : Props {
    var student: Student
}

val CStudentInList = FC<StudentInListProps>("StudentInList") { props ->
    span {
        +props.student.fullname()
    }
}