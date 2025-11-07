package com.workable.mobile.data

data class Job(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val type: String,
    val companyLogo: String
)

val sampleJobs = listOf(
    Job("1", "Accessibility Tester", "Inclusive Labs", "Remote", "Full-time", "https://via.placeholder.com/56"),
    Job("2", "Frontend Developer (Kotlin/Compose)", "WorkAble", "Manila, PH", "Contract", "https://via.placeholder.com/56"),
    Job("3", "Support Specialist", "Care Corp", "Cebu, PH", "Part-time", "https://via.placeholder.com/56")
)
