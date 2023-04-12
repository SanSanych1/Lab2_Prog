package com.sovostyanov.application.repo

import com.sovostyanov.application.data.Grade
import com.sovostyanov.application.data.GradeInfo
import com.sovostyanov.application.data.Lesson
import com.sovostyanov.application.data.Student
import com.sovostyanov.application.repo.ListRepo

val studentsRepo = ListRepo<Student>()
val lessonsRepo = ListRepo<Lesson>()

val menuList = listOf("students","lessons","groups")
fun createTestData() {
    listOf(
        Student("Sheldon", "Cooper","29m"),
        Student("Leonard", "Hofstadter","20z"),
        Student("Howard", "Wolowitz","29m"),
        Student("Penny", "Hofstadter","20m"),
    ).apply {
        map {
            studentsRepo.create(it)
        }
    }

    listOf(
        Lesson("Math"),
        Lesson("Phys"),
        Lesson("Story"),
    ).apply {
        map {
            lessonsRepo.create(it)
        }
    }

    val students = studentsRepo.read()
    val lessons = lessonsRepo.read()
    val sheldon = students.findLast { it.elem.firstname == "Sheldon" }
    check(sheldon != null)
    val leonard = students.findLast { it.elem.firstname == "Leonard" }
    check(leonard != null)
    val math = lessons.findLast { it.elem.name =="Math" }
    check(math != null)
    val newMath = Lesson(
        math.elem.name,
        arrayOf(
            GradeInfo(sheldon.id, Grade.A),
            GradeInfo(leonard.id, Grade.B)
        ))
    lessonsRepo.update(math.id, newMath)
}
