package component.student

import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.span
import react.useState
import com.sovostyanov.application.common.Item
import com.sovostyanov.application.common.ItemId
import com.sovostyanov.application.data.Student

external interface QueryError

external interface StudentListProps : Props {
    var students: Array<Item<Student>>
    var deleteStudent: (ItemId) -> Unit
    var updateStudent: (Item<Student>) -> Unit
}

val CStudentList = FC<StudentListProps>("StudentList") { props ->
    var editedId by useState<String>("")
    ol {
        props.students.forEach { studentItem ->
            li {
                if (studentItem.id == editedId)
                    CEditStudent {
                        oldStudent = studentItem.elem
                        saveStudent = {
                            props.updateStudent(Item(it, studentItem.id))
                            editedId = ""
                        }
                    }
                else
                    CStudentInList {
                        student = studentItem.elem
                    }
                span {
                    +" ✂ "
                    onClick = {
                        props.deleteStudent(studentItem.id)
                    }
                }
                span {
                    +" ✎ "
                    onClick = {
                        editedId = studentItem.id
                    }
                }
            }
        }
    }
}
