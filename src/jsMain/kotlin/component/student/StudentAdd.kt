package component.student

import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.useRef
import com.sovostyanov.application.data.Student
import component.template.EditAddProps
import react.dom.html.ReactHTML.span
import react.useState
import web.html.HTMLInputElement
import web.html.InputType

val CStudentAdd = FC<EditAddProps<Student>>("StudentAdd") { props ->
    var firstname by useState("")
    var surname by useState("")
    var group by useState("")
    span {
        input {
            type = InputType.text
            value = firstname
            onChange = { firstname = it.target.value }
        }
        input {
            type = InputType.text
            value = surname
            onChange = { surname = it.target.value }
        }
        input {
            type = InputType.text
            value = group
            onChange = { group = it.target.value }
        }
    }
    button {
        +"✓"
        onClick = {
            props.saveElement(Student(firstname, surname, group))
        }
    }
}