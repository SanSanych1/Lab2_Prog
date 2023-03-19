package com.sovostyanov.application.repo

import com.sovostyanov.application.data.Student

val studentsRepo = ListRepo<Student>()
val menuList = arrayOf("students","groups")

fun createTestData() {
    listOf(
        Student("Sheldon", "Cooper", "29m"),
        Student("Leonard", "Hofstadter", "29z"),
        Student("Howard", "Wolowitz", "29z"),
        Student("Penny", "Hofstadter", "29m"),
        Student("Sheldon", "Cooper", "29m"),
        Student("Leonard", "Hofstadter", "29i"),
        Student("Howard", "Wolowitz", "29z"),
        Student("Penny", "Hofstadter", "29m"),
        Student("Sheldon", "Cooper", "29m"),
        Student("Leonard", "Hofstadter", "29u"),
        Student("Howard", "Wolowitz", "29u"),
        Student("Penny", "Hofstadter", "29m"),
    ).apply {
        map {
            studentsRepo.create(it)
        }
    }
}
