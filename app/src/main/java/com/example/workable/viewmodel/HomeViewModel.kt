package com.example.jobsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobsearch.model.Job

class HomeViewModel : ViewModel() {

    // Greeting / welcome message
    private val _welcomeMessage = MutableLiveData<String>()
    val welcomeMessage: LiveData<String> = _welcomeMessage

    // Featured jobs or recommended jobs
    private val _featuredJobs = MutableLiveData<List<Job>>()
    val featuredJobs: LiveData<List<Job>> = _featuredJobs

    // Loading and error states for UI
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        _loading.value = true
        try {
            // Dummy data for demonstration (replace with repository call later)
            _welcomeMessage.value = "Welcome back, User 👋"

            val sampleJobs = listOf(
                Job(
                    id = 1,
                    title = "Junior Android Developer",
                    company = "TechNova",
                    location = "San Francisco, USA",
                    description = "Build Android apps using Kotlin and Jetpack Compose."
                ),
                Job(
                    id = 2,
                    title = "Data Analyst",
                    company = "Insight Corp",
                    location = "Toronto, Canada",
                    description = "Analyze data trends and prepare insights reports."
                ),
                Job(
                    id = 3,
                    title = "UI/UX Designer",
                    company = "DesignHub",
                    location = "Remote",
                    description = "Design modern, user-friendly mobile app interfaces."
                )
            )

            _featuredJobs.value = sampleJobs
            _error.value = null

        } catch (e: Exception) {
            _error.value = e.localizedMessage
        } finally {
            _loading.value = false
        }
    }

    fun refreshHome() {
        // Optionally allow refreshing (e.g. pull-to-refresh)
        loadHomeData()
    }
}
