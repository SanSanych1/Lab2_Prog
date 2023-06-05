package component.student.list

import js.core.jso
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import com.sovostyanov.application.common.Item
import com.sovostyanov.application.common.ItemId
import com.sovostyanov.application.config.Config
import com.sovostyanov.application.data.Lesson
import com.sovostyanov.application.data.Student
import component.student.CAddLesson
import component.student.CStudentLessons
import js.core.get
import kotlinx.serialization.encodeToString
import query.QueryError
import react.router.useParams
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQuery
import tanstack.react.query.useQueryClient
import tools.HTTPResult
import tools.fetch
import tools.fetchText
import kotlin.js.json


val AddLessonContainer = FC("AddLesssonContainer") { _: Props ->
    val queryClient = useQueryClient()
    val findLessonByRegex = arrayOf("findLessonByRegex").unsafeCast<QueryKey>()

    val SendRegexMutation = useMutation<HTTPResult, Any, String, Any>(
        mutationFn = { element: String ->
            fetch(
                Config.lessonsPath + "byRegex/",
                jso {
                    method = "GET"
                    headers = json("Content-Type" to "application/json")
                    body = element
                }
            )
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>(findLessonByRegex)
            }
        }
    )
    val AddLessonToStudentMutation = useMutation<HTTPResult, Any, String, Any>(
        mutationFn = { element: String ->
            fetch(
                Config.lessonsPath + "byRegex/",
                jso {
                    method = "POST"
                    headers = json("Content-Type" to "application/json")
                    body = element
                }
            )
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>(findLessonByRegex)
            }
        }
    )
    CAddLesson {
        this.lessons = arrayOf()
        this.SendRegexAction = { SendRegexMutation.mutateAsync("",null) }
        this.AddLessonAction = { AddLessonToStudentMutation.mutateAsync("", null) }
    }

}