package component.lesson

import com.sovostyanov.application.data.Lesson
import component.template.ElementInListProps
import react.FC
import react.dom.html.ReactHTML.span


val CLessonInList = FC<ElementInListProps<Lesson>>("LessonInList") { props ->
    span {
        +props.element.name
    }
}