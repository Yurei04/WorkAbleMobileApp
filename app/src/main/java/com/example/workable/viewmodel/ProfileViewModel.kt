package com.example.jobsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class UserProfile(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val profileImageUrl: String = "",
    val savedJobs: List<Int> = emptyList() // store job IDs
)

class ProfileViewModel : ViewModel() {

    // Mutable private data
    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    // Status for profile loading (useful for showing progress bar or errors)
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        // Load user profile when ViewModel is created (you can later replace with Firebase)
        loadUserProfile()
    }

    private fun loadUserProfile() {
        _loading.value = true

        try {
            // Dummy data (replace with Firebase or backend call)
            val dummyProfile = UserProfile(
                id = "12345",
                name = "John Doe",
                email = "john.doe@example.com",
                profileImageUrl = "https://example.com/avatar.jpg",
                savedJobs = listOf(1, 3)
            )

            _userProfile.value = dummyProfile
            _error.value = null

        } catch (e: Exception) {
            _error.value = e.localizedMessage
        } finally {
            _loading.value = false
        }
    }

    fun updateUserName(newName: String) {
        _userProfile.value = _userProfile.value?.copy(name = newName)
    }

    fun updateProfileImage(newUrl: String) {
        _userProfile.value = _userProfile.value?.copy(profileImageUrl = newUrl)
    }

    fun saveJob(jobId: Int) {
        val current = _userProfile.value ?: return
        if (!current.savedJobs.contains(jobId)) {
            _userProfile.value = current.copy(savedJobs = current.savedJobs + jobId)
        }
    }

    fun removeSavedJob(jobId: Int) {
        val current = _userProfile.value ?: return
        _userProfile.value = current.copy(savedJobs = current.savedJobs - jobId)
    }
}
