package component.student

import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span
import react.useRef
import com.sovostyanov.application.data.Student
import component.template.EditItemProps
import react.dom.html.ReactHTML.button
import react.useState
import web.html.HTMLInputElement
import web.html.InputType

val CStudentEdit = FC<EditItemProps<Student>>("StudentEdit") { props ->
    var firstname by useState(props.item.elem.firstname)
    var surname by useState(props.item.elem.surname)
    var group by useState(props.item.elem.group)
    span {
        input {
            type = InputType.text
            value = firstname
            onChange = { firstname = it.target.value }
        }
        input {
            type = InputType.text
            value = surname
            onChange = {surname = it.target.value }
        }
        input {
            type = InputType.text
            value = group
            onChange = {group = it.target.value }
        }
    }
    button {
        +"✓"
        onClick = {
            props.saveElement(Student(firstname, surname, group))
        }
    }
}
