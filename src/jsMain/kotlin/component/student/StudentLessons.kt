package component.student

import com.sovostyanov.application.common.Item
import com.sovostyanov.application.data.Lesson
import component.template.ElementInListProps
import react.FC
import react.dom.html.ReactHTML.span
import com.sovostyanov.application.data.Student
import component.lesson.GradeInfoFull
import component.student.list.AddLessonContainer
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.ul

external interface StudentLessonsProps : Props {
    var lessons: Array<Item<Lesson>>
}

val CStudentLessons = FC<StudentLessonsProps>("StudentLessons") { props ->
    AddLessonContainer{}
    ul{
        for (lesson in props.lessons){
            li{
                +lesson.elem.name
            }
        }
    }
}