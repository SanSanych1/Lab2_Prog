package component.student

import com.sovostyanov.application.common.Item
import com.sovostyanov.application.data.Lesson
import component.template.ElementInListProps
import react.FC
import react.dom.html.ReactHTML.span
import com.sovostyanov.application.data.Student
import component.lesson.GradeInfoFull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import react.Props
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.dom.html.ReactHTML.ul
import react.useRef
import react.useState
import web.html.InputType

external interface AddLessonProps : Props {
    var lessons: Array<Item<Lesson>>
    var SendRegexAction: () -> Unit //(String) -> Array<Item<Lesson>>
    var AddLessonAction: () -> Unit
}

val CAddLesson = FC<AddLessonProps>("AddLesson") { props ->
    var lessons by useState(props.lessons)
    var regexText by useState("")
    var selectedLesson by useState<Item<Lesson>>()

    input{
        type = InputType.text
        value = regexText
    }
    input{
        type = InputType.button
        value = "Send regex"
        onClick = { props.SendRegexAction() }
    }
    select{
        for (lesson in lessons)
        {
            option{
                value = Json.encodeToString(lesson)
                +lesson.elem.name
            }
        }
        onSelect = {
            selectedLesson = Json.decodeFromString<Item<Lesson>>(it.currentTarget.value)
        }
    }
    input{
        type = InputType.button
        value = "Send regex"
        onClick = { props.AddLessonAction() }
    }
}

