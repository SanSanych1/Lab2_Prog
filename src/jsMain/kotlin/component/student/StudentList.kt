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
import csstype.*
import emotion.css.css
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.router.dom.Link

external interface QueryError

external interface StudentListProps : Props {
    var students: Array<Item<Student>>
    var deleteStudent: (ItemId) -> Unit
    var updateStudent: (Item<Student>) -> Unit
}

val CStudentList = FC<StudentListProps>("StudentList") { props ->
    var editedId by useState<String>("")
    ol {
        css {
            display = Display.flex
            boxShadow = BoxShadow(0.px, 0.px, 6.px, rgba(32, 33, 36, 0.28))
            background = rgb(255, 255, 255);
            borderRadius = 24.px;
            padding = 10.px
            listStyleType = None.none
            flexWrap = FlexWrap.wrap
            maxWidth = 40.pct
            minWidth = 450.px
            margin = Auto.auto
        }
        props.students.forEach { studentItem ->
            Link {
                css {
                    display = Display.block
                    width = 100.pct
                    textDecoration = None.none
                    padding = 0.px
                    margin = 5.px
                }


                li {
                    css {
                        display = Display.flex
                        justifyContent = JustifyContent.spaceBetween
                        border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                        background = rgb(255, 255, 255);
                        borderRadius = 24.px;
                        padding = 8.px
                    }
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
                    div {
                        css {
                            display = Display.flex
                            alignItems = AlignItems.center
                        }
                        span {
                            css {
                                textAlign = TextAlign.center
                                background = Color("#3466ff")
                                width = 30.px
                                height = 30.px
                                lineHeight = 30.px
                                color = Color("#FFF")
                                borderRadius = 100.pct;
                                margin = Margin(0.px, 5.px)
                                background = Color("#ff3452")
                            }
                            +" ✖ "
                            onClick = {
                                props.deleteStudent(studentItem.id)
                            }
                        }
                        span {
                            css {
                                textAlign = TextAlign.center
                                background = Color("#3466ff")
                                width = 30.px
                                height = 30.px
                                lineHeight = 30.px
                                color = Color("#FFF")
                                borderRadius = 100.pct;
                                margin = Margin(0.px, 5.px)
                            }
                            +" ✎ "
                            onClick = {
                                editedId = studentItem.id
                            }
                        }
                    }
                }
            }
        }
    }
}
