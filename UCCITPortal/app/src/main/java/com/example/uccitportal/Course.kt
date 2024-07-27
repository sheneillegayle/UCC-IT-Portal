package com.example.uccitportal

// Data class for the course
data class Course(
    val id: String = "",
    val code: String = "",
    val name: String = "",
    val credits: Int = 0,
    val prerequisites: String = "",
    val description: String = ""
)
