package com.sovostyanov.application.repo

import com.sovostyanov.application.data.Student

val studentsRepo = ListRepo<Student>()

fun createTestData() {
    listOf(
        Student("Sheldon", "Cooper", "29m"),
        Student("Leonard", "Hofstadter", "29z"),
        Student("Howard", "Wolowitz", "29z"),
        Student("Penny", "Hofstadter", "29m"),
    ).apply {
        map {
            studentsRepo.create(it)
        }
    }
}
