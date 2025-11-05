package com.example.workable.ui.pages.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.workable.databinding.PageSettingsBinding

class SettingsPage : Fragment() {

    private lateinit var binding: PageSettingsBinding
    private val viewModel: SettingsViewModel by viewModels() // Using MVVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe settings data if needed
        // Example: viewModel.notificationsEnabled.observe(viewLifecycleOwner) { enabled -> ... }

        // Change Password
        binding.changePasswordButton.setOnClickListener {
            // TODO: Navigate to ChangePasswordPage
        }

        // Toggle Notifications
        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotificationsEnabled(isChecked)
        }

        // Logout
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            // TODO: Navigate to LoginPage
        }

        // Toggle Dark Mode
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkModeEnabled(isChecked)
            // Optionally apply theme changes
        }
    }
}
