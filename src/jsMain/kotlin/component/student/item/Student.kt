package component.student.item

import com.sovostyanov.application.common.Item
import react.FC
import react.Props
import react.dom.html.ReactHTML.span
import com.sovostyanov.application.data.Student
import csstype.*
import emotion.react.css
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p
import react.useRef
import react.useState
import web.html.HTMLInputElement

external interface StudentProps : Props {
    var student: Item<Student>
    var saveStudent: (Item<Student>) -> Unit
}

val CStudent = FC<StudentProps>("Student") { props ->
    var editngField by useState("")
    val firstnameRef = useRef<HTMLInputElement>()
    val surnameRef = useRef<HTMLInputElement>()
    val groupRef = useRef<HTMLInputElement>()
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
                fontWeight = FontWeight.bold
            }
            +"Firstname"
        }
        if(editngField == "firstname")
        {
            input {
                css {
                    flexBasis = 40.pct
                    border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                    background = rgb(255, 255, 255);
                    borderRadius = 24.px;
                    padding = 8.px
                    textAlign = TextAlign.center
                }
                defaultValue = props.student.elem.firstname
                ref = firstnameRef
            }
            span {
                css {
                    textAlign = TextAlign.center
                    background = Color("#00b81d")
                    width = 30.px
                    height = 30.px
                    lineHeight = 30.px
                    color = Color("#FFF")
                    borderRadius = 100.pct;
                    margin = Margin(0.px, 5.px)
                }
                +"✔"
                onClick = {
                    editngField = ""
                    props.saveStudent(Item(Student(firstnameRef.current!!.value,props.student.elem.surname,props.student.elem.group),props.student.id))
                }
            }
        }
        else
        {
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
                    editngField = "firstname"
                }
            }
        }


        p {
            css {
                flexBasis = 40.pct
                fontWeight = FontWeight.bold
            }
            +"Surname"
        }
        if(editngField == "surname")
        {
            input {
                css {
                    flexBasis = 40.pct
                    border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                    background = rgb(255, 255, 255);
                    borderRadius = 24.px;
                    padding = 8.px
                    textAlign = TextAlign.center
                }
                defaultValue = props.student.elem.surname
                ref = surnameRef
            }
            span {
                css {
                    textAlign = TextAlign.center
                    background = Color("#00b81d")
                    width = 30.px
                    height = 30.px
                    lineHeight = 30.px
                    color = Color("#FFF")
                    borderRadius = 100.pct;
                    margin = Margin(0.px, 5.px)
                }
                +"✔"
                onClick = {
                    editngField = ""
                    props.saveStudent(Item(Student(props.student.elem.firstname,surnameRef.current!!.value,props.student.elem.group),props.student.id))
                }
            }
        }
        else
        {
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
                    editngField = "surname"
                }
            }
        }

        p {
            css {
                flexBasis = 40.pct
                fontWeight = FontWeight.bold
            }
            +"Group"
        }
        if(editngField == "group")
        {
            input {
                css {
                    flexBasis = 40.pct
                    border = Border(1.px, LineStyle.solid, Color("#dfe1e5"))
                    background = rgb(255, 255, 255);
                    borderRadius = 24.px;
                    padding = 8.px
                    textAlign = TextAlign.center
                }
                defaultValue = props.student.elem.group
                ref = groupRef
            }
            span {
                css {
                    textAlign = TextAlign.center
                    background = Color("#00b81d")
                    width = 30.px
                    height = 30.px
                    lineHeight = 30.px
                    color = Color("#FFF")
                    borderRadius = 100.pct;
                    margin = Margin(0.px, 5.px)
                }
                +"✔"
                onClick = {
                    editngField = ""
                    props.saveStudent(Item(Student(props.student.elem.firstname,props.student.elem.surname,groupRef.current!!.value),props.student.id))
                }
            }
        }
        else {
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
                    editngField = "group"
                }
            }
        }
    }
}